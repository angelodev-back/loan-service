package pe.edu.idat.loan_service.service;

import pe.edu.idat.loan_service.dto.request.PrestamoRequestDTO;
import pe.edu.idat.loan_service.dto.response.PrestamoResponseDTO;
import java.util.List;

public interface IPrestamoService {
    PrestamoResponseDTO realizarPrestamo(PrestamoRequestDTO dto, String usuario);
    PrestamoResponseDTO realizarDevolucion(Integer idPrestamo, String usuario);
    List<PrestamoResponseDTO> listarPrestamosActivos();
    List<PrestamoResponseDTO> listarPrestamosPorUsuario(Integer idUsuario);
    List<PrestamoResponseDTO> listarTodos();
}