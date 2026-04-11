package pe.edu.idat.loan_service.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.loan_service.dto.request.ReservaRequestDTO;
import pe.edu.idat.loan_service.dto.response.ReservaResponseDTO;
import pe.edu.idat.loan_service.service.IReservaService;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final IReservaService reservaService;

    public ReservaController(IReservaService reservaService) {
        this.reservaService = reservaService;
    }

    private String getUsuarioActual() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'BIBLIOTECARIO', 'LECTOR')")
    public ResponseEntity<ReservaResponseDTO> realizarReserva(@Valid @RequestBody ReservaRequestDTO request) {
        ReservaResponseDTO response = reservaService.realizarReserva(request, getUsuarioActual());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}/cancelar")
    @PreAuthorize("hasAnyRole('ADMIN', 'BIBLIOTECARIO')")
    public ResponseEntity<ReservaResponseDTO> cancelarReserva(@PathVariable Integer id) {
        ReservaResponseDTO response = reservaService.cancelarReserva(id, getUsuarioActual());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/{idUsuario}")
    @PreAuthorize("hasAnyRole('ADMIN', 'BIBLIOTECARIO')")
    public ResponseEntity<List<ReservaResponseDTO>> listarReservasPorUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(reservaService.listarReservasPorUsuario(idUsuario));
    }

    @GetMapping("/pendientes")
    @PreAuthorize("hasAnyRole('ADMIN', 'BIBLIOTECARIO')")
    public ResponseEntity<List<ReservaResponseDTO>> listarReservasPendientes() {
        return ResponseEntity.ok(reservaService.listarReservasPendientes());
    }
}