package pe.edu.idat.loan_service.service;

import pe.edu.idat.loan_service.dto.request.ReservaRequestDTO;
import pe.edu.idat.loan_service.dto.response.ReservaResponseDTO;
import java.util.List;

public interface IReservaService {
    ReservaResponseDTO realizarReserva(ReservaRequestDTO dto, String usuario);
    ReservaResponseDTO cancelarReserva(Integer idReserva, String usuario);
    void procesarReservasPendientes(Integer idLibro);
    List<ReservaResponseDTO> listarReservasPorUsuario(Integer idUsuario);
    List<ReservaResponseDTO> listarReservasPendientes();
}