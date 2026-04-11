package pe.edu.idat.loan_service.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idat.loan_service.client.BookServiceClient;
import pe.edu.idat.loan_service.dto.request.PrestamoRequestDTO;
import pe.edu.idat.loan_service.dto.response.PrestamoResponseDTO;
import pe.edu.idat.loan_service.entity.Prestamo;
import pe.edu.idat.loan_service.exception.MaximoLibrosException;
import pe.edu.idat.loan_service.exception.PrestamoNotFoundException;
import pe.edu.idat.loan_service.mapper.PrestamoMapper;
import pe.edu.idat.loan_service.repository.PrestamoRepository;
import pe.edu.idat.loan_service.service.EnriquecimientoService;
import pe.edu.idat.loan_service.service.IPrestamoService;
import pe.edu.idat.loan_service.service.IReservaService;
import pe.edu.idat.loan_service.service.ValidacionService;
import pe.edu.idat.loan_service.utils.MultaCalculator;

@Service
public class PrestamoServiceImpl implements IPrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final BookServiceClient bookServiceClient;
    private final IReservaService reservaService;
    private final PrestamoMapper prestamoMapper;
    private final MultaCalculator multaCalculator;
    private final ValidacionService validacionService;
    private final EnriquecimientoService enriquecimientoService;

    @Value("${prestamo.dias.prestamo:7}")
    private int diasPrestamo;

    @Value("${prestamo.max.libros:3}")
    private int maxLibros;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository,
                               BookServiceClient bookServiceClient,
                               IReservaService reservaService,
                               PrestamoMapper prestamoMapper,
                               MultaCalculator multaCalculator,
                               ValidacionService validacionService,
                               EnriquecimientoService enriquecimientoService) {
        this.prestamoRepository = prestamoRepository;
        this.bookServiceClient = bookServiceClient;
        this.reservaService = reservaService;
        this.prestamoMapper = prestamoMapper;
        this.multaCalculator = multaCalculator;
        this.validacionService = validacionService;
        this.enriquecimientoService = enriquecimientoService;
    }

    @Override
    @Transactional
    public PrestamoResponseDTO realizarPrestamo(PrestamoRequestDTO dto, String usuario) {
        validacionService.validarUsuario(dto.getIdUsuario());

        long prestamosActivos = prestamoRepository.countPrestamosActivosByUsuario(dto.getIdUsuario());
        if (prestamosActivos >= maxLibros) {
            throw new MaximoLibrosException("El usuario ya tiene " + maxLibros + " libros prestados");
        }

        validacionService.validarLibro(dto.getIdLibro());
        bookServiceClient.prestarLibro(dto.getIdLibro());

        Prestamo prestamo = prestamoMapper.requestToEntity(dto);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaLimite(LocalDate.now().plusDays(diasPrestamo));
        prestamo.setEstado("ACTIVO");
        prestamo.setMulta(BigDecimal.ZERO);
        prestamo.setCreatedBy(usuario);
        prestamo.setCreatedDate(LocalDateTime.now());

        Prestamo saved = prestamoRepository.save(prestamo);
        return enriquecimientoService.enriquecerPrestamo(saved);
    }

    @Override
    @Transactional
    public PrestamoResponseDTO realizarDevolucion(Integer idPrestamo, String usuario) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new PrestamoNotFoundException("Préstamo no encontrado con ID: " + idPrestamo));

        if ("DEVUELTO".equals(prestamo.getEstado())) {
            throw new PrestamoNotFoundException("El préstamo ya fue devuelto");
        }

        LocalDate fechaDevolucion = LocalDate.now();
        BigDecimal multa = multaCalculator.calcularMulta(prestamo.getFechaLimite(), fechaDevolucion);

        prestamo.setFechaDevolucion(fechaDevolucion);
        prestamo.setEstado("DEVUELTO");
        prestamo.setMulta(multa);
        prestamo.setModifiedBy(usuario);
        prestamo.setLastModifiedDate(LocalDateTime.now());

        bookServiceClient.devolverLibro(prestamo.getIdLibro());

        Prestamo saved = prestamoRepository.save(prestamo);
        reservaService.procesarReservasPendientes(prestamo.getIdLibro());

        return enriquecimientoService.enriquecerPrestamo(saved);
    }

    @Override
    public List<PrestamoResponseDTO> listarPrestamosActivos() {
        return prestamoRepository.findByEstado("ACTIVO").stream()
                .map(enriquecimientoService::enriquecerPrestamo)
                .toList();
    }

    @Override
    public List<PrestamoResponseDTO> listarPrestamosPorUsuario(Integer idUsuario) {
        return prestamoRepository.findByIdUsuario(idUsuario).stream()
                .map(enriquecimientoService::enriquecerPrestamo)
                .toList();
    }

    @Override
    public List<PrestamoResponseDTO> listarTodos() {
        return prestamoRepository.findAll().stream()
                .map(enriquecimientoService::enriquecerPrestamo)
                .toList();
    }
}