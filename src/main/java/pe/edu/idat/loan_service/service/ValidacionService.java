package pe.edu.idat.loan_service.service;

import feign.FeignException;
import org.springframework.stereotype.Service;
import pe.edu.idat.loan_service.client.BookServiceClient;
import pe.edu.idat.loan_service.client.UserServiceClient;
import pe.edu.idat.loan_service.dto.external.LibroDisponibilidadDTO;
import pe.edu.idat.loan_service.exception.LibroNoDisponibleException;
import pe.edu.idat.loan_service.exception.UsuarioInactivoException;

@Service
public class ValidacionService {

    private final UserServiceClient userServiceClient;
    private final BookServiceClient bookServiceClient;

    public ValidacionService(UserServiceClient userServiceClient,
                             BookServiceClient bookServiceClient) {
        this.userServiceClient = userServiceClient;
        this.bookServiceClient = bookServiceClient;
    }

    public void validarUsuario(Integer idUsuario) {
        try {
            if (!userServiceClient.existeUsuario(idUsuario)) {
                throw new UsuarioInactivoException("Usuario no encontrado con ID: " + idUsuario);
            }
            if (!userServiceClient.validarUsuarioActivo(idUsuario)) {
                throw new UsuarioInactivoException("Usuario no está activo");
            }
        } catch (FeignException e) {
            throw new UsuarioInactivoException("Error al validar usuario: " + e.getMessage());
        }
    }

    public LibroDisponibilidadDTO validarLibro(Integer idLibro) {
        try {
            LibroDisponibilidadDTO disponibilidad = bookServiceClient.validarDisponibilidad(idLibro);
            if (!disponibilidad.isPuedePrestar()) {
                throw new LibroNoDisponibleException("Libro no disponible para préstamo");
            }
            return disponibilidad;
        } catch (FeignException e) {
            throw new LibroNoDisponibleException("Error al validar libro: " + e.getMessage());
        }
    }
}