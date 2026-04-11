package pe.edu.idat.loan_service.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idat.loan_service.client.BookServiceClient;
import pe.edu.idat.loan_service.dto.request.ReservaRequestDTO;
import pe.edu.idat.loan_service.dto.response.ReservaResponseDTO;
import pe.edu.idat.loan_service.entity.Reserva;
import pe.edu.idat.loan_service.exception.ReservaNotFoundException;
import pe.edu.idat.loan_service.exception.ReservaYaExisteException;
import pe.edu.idat.loan_service.mapper.ReservaMapper;
import pe.edu.idat.loan_service.repository.ReservaRepository;
import pe.edu.idat.loan_service.service.EnriquecimientoService;
import pe.edu.idat.loan_service.service.IReservaService;
import pe.edu.idat.loan_service.service.ValidacionService;

@Service
public class ReservaServiceImpl implements IReservaService {

    private final ReservaRepository reservaRepository;
    private final BookServiceClient bookServiceClient;
    private final ReservaMapper reservaMapper;
    private final ValidacionService validacionService;
    private final EnriquecimientoService enriquecimientoService;

    public ReservaServiceImpl(ReservaRepository reservaRepository,
                              BookServiceClient bookServiceClient,
                              ReservaMapper reservaMapper,
                              ValidacionService validacionService,
                              EnriquecimientoService enriquecimientoService) {
        this.reservaRepository = reservaRepository;
        this.bookServiceClient = bookServiceClient;
        this.reservaMapper = reservaMapper;
        this.validacionService = validacionService;
        this.enriquecimientoService = enriquecimientoService;
    }

    @Override
    @Transactional
    public ReservaResponseDTO realizarReserva(ReservaRequestDTO dto, String usuario) {
        validacionService.validarUsuario(dto.getIdUsuario());

        reservaRepository.findByUsuarioAndLibro(dto.getIdUsuario(), dto.getIdLibro())
                .ifPresent(r -> {
                    if (!"CANCELADA".equals(r.getEstado())) {
                        throw new ReservaYaExisteException("Ya existe una reserva activa para este libro");
                    }
                });

        bookServiceClient.validarDisponibilidad(dto.getIdLibro());

        Reserva reserva = reservaMapper.requestToEntity(dto);
        reserva.setFechaReserva(LocalDate.now());
        reserva.setEstado("PENDIENTE");
        reserva.setCreatedBy(usuario);
        reserva.setCreatedDate(LocalDateTime.now());

        Reserva saved = reservaRepository.save(reserva);
        return enriquecimientoService.enriquecerReserva(saved);
    }

    @Override
    @Transactional
    public ReservaResponseDTO cancelarReserva(Integer idReserva, String usuario) {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new ReservaNotFoundException("Reserva no encontrada con ID: " + idReserva));

        reserva.setEstado("CANCELADA");
        reserva.setModifiedBy(usuario);
        reserva.setLastModifiedDate(LocalDateTime.now());

        Reserva saved = reservaRepository.save(reserva);
        return enriquecimientoService.enriquecerReserva(saved);
    }

    @Override
    @Transactional
    public void procesarReservasPendientes(Integer idLibro) {
        List<Reserva> reservasPendientes = reservaRepository.findReservasPendientesByLibro(idLibro);

        if (!reservasPendientes.isEmpty()) {
            Reserva primeraReserva = reservasPendientes.get(0);
            primeraReserva.setEstado("COMPLETADA");
            primeraReserva.setLastModifiedDate(LocalDateTime.now());
            reservaRepository.save(primeraReserva);

            for (int i = 1; i < reservasPendientes.size(); i++) {
                Reserva reserva = reservasPendientes.get(i);
                reserva.setEstado("CANCELADA");
                reserva.setLastModifiedDate(LocalDateTime.now());
                reservaRepository.save(reserva);
            }
        }
    }

    @Override
    public List<ReservaResponseDTO> listarReservasPorUsuario(Integer idUsuario) {
        return reservaRepository.findByIdUsuario(idUsuario).stream()
                .map(enriquecimientoService::enriquecerReserva)
                .toList();
    }

    @Override
    public List<ReservaResponseDTO> listarReservasPendientes() {
        return reservaRepository.findByEstado("PENDIENTE").stream()
                .map(enriquecimientoService::enriquecerReserva)
                .toList();
    }
}