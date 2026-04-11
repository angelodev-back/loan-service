package pe.edu.idat.loan_service.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.loan_service.dto.request.DevolucionRequestDTO;
import pe.edu.idat.loan_service.dto.request.PrestamoRequestDTO;
import pe.edu.idat.loan_service.dto.response.PrestamoResponseDTO;
import pe.edu.idat.loan_service.service.IPrestamoService;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final IPrestamoService prestamoService;

    public PrestamoController(IPrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    private String getUsuarioActual() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'BIBLIOTECARIO')")
    public ResponseEntity<PrestamoResponseDTO> realizarPrestamo(@Valid @RequestBody PrestamoRequestDTO request) {
        PrestamoResponseDTO response = prestamoService.realizarPrestamo(request, getUsuarioActual());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/devolucion")
    @PreAuthorize("hasAnyRole('ADMIN', 'BIBLIOTECARIO')")
    public ResponseEntity<PrestamoResponseDTO> realizarDevolucion(@Valid @RequestBody DevolucionRequestDTO request) {
        PrestamoResponseDTO response = prestamoService.realizarDevolucion(request.getIdPrestamo(), getUsuarioActual());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/activos")
    @PreAuthorize("hasAnyRole('ADMIN', 'BIBLIOTECARIO')")
    public ResponseEntity<List<PrestamoResponseDTO>> listarPrestamosActivos() {
        return ResponseEntity.ok(prestamoService.listarPrestamosActivos());
    }

    @GetMapping("/usuario/{idUsuario}")
    @PreAuthorize("hasAnyRole('ADMIN', 'BIBLIOTECARIO')")
    public ResponseEntity<List<PrestamoResponseDTO>> listarPrestamosPorUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(prestamoService.listarPrestamosPorUsuario(idUsuario));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'BIBLIOTECARIO')")
    public ResponseEntity<List<PrestamoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(prestamoService.listarTodos());
    }
}