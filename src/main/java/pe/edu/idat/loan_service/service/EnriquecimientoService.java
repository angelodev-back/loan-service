package pe.edu.idat.loan_service.service;

import org.springframework.stereotype.Service;
import pe.edu.idat.loan_service.client.BookServiceClient;
import pe.edu.idat.loan_service.client.UserServiceClient;
import pe.edu.idat.loan_service.dto.external.LibroBasicoDTO;
import pe.edu.idat.loan_service.dto.external.UsuarioBasicoDTO;
import pe.edu.idat.loan_service.dto.response.PrestamoResponseDTO;
import pe.edu.idat.loan_service.dto.response.ReservaResponseDTO;
import pe.edu.idat.loan_service.entity.Prestamo;
import pe.edu.idat.loan_service.entity.Reserva;
import pe.edu.idat.loan_service.mapper.PrestamoMapper;
import pe.edu.idat.loan_service.mapper.ReservaMapper;

@Service
public class EnriquecimientoService {

    private final UserServiceClient userServiceClient;
    private final BookServiceClient bookServiceClient;
    private final PrestamoMapper prestamoMapper;
    private final ReservaMapper reservaMapper;

    public EnriquecimientoService(UserServiceClient userServiceClient,
                                  BookServiceClient bookServiceClient,
                                  PrestamoMapper prestamoMapper,
                                  ReservaMapper reservaMapper) {
        this.userServiceClient = userServiceClient;
        this.bookServiceClient = bookServiceClient;
        this.prestamoMapper = prestamoMapper;
        this.reservaMapper = reservaMapper;
    }

    public PrestamoResponseDTO enriquecerPrestamo(Prestamo prestamo) {
        PrestamoResponseDTO response = prestamoMapper.toResponseDTO(prestamo);

        try {
            UsuarioBasicoDTO usuario = userServiceClient.obtenerDatosBasicos(prestamo.getIdUsuario());
            response.setNombreUsuario(usuario.getNombreCompleto());
        } catch (Exception e) {
            response.setNombreUsuario("Usuario #" + prestamo.getIdUsuario());
        }

        try {
            LibroBasicoDTO libro = bookServiceClient.obtenerDatosBasicos(prestamo.getIdLibro());
            response.setTituloLibro(libro.getTitulo());
        } catch (Exception e) {
            response.setTituloLibro("Libro #" + prestamo.getIdLibro());
        }

        return response;
    }

    public ReservaResponseDTO enriquecerReserva(Reserva reserva) {
        ReservaResponseDTO response = reservaMapper.toResponseDTO(reserva);

        try {
            UsuarioBasicoDTO usuario = userServiceClient.obtenerDatosBasicos(reserva.getIdUsuario());
            response.setNombreUsuario(usuario.getNombreCompleto());
        } catch (Exception e) {
            response.setNombreUsuario("Usuario #" + reserva.getIdUsuario());
        }

        try {
            LibroBasicoDTO libro = bookServiceClient.obtenerDatosBasicos(reserva.getIdLibro());
            response.setTituloLibro(libro.getTitulo());
        } catch (Exception e) {
            response.setTituloLibro("Libro #" + reserva.getIdLibro());
        }

        return response;
    }
}