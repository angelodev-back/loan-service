package pe.edu.idat.loan_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.idat.loan_service.dto.external.LibroBasicoDTO;
import pe.edu.idat.loan_service.dto.external.LibroDisponibilidadDTO;

@FeignClient(name = "BOOK-SERVICE")
public interface BookServiceClient {

    @GetMapping("/api/internal/libros/{id}/validar")
    LibroDisponibilidadDTO validarDisponibilidad(@PathVariable("id") Integer id);

    @PostMapping("/api/internal/libros/{id}/prestar")
    void prestarLibro(@PathVariable("id") Integer id);

    @PostMapping("/api/internal/libros/{id}/devolver")
    void devolverLibro(@PathVariable("id") Integer id);

    @GetMapping("/api/internal/libros/{id}/basico")
    LibroBasicoDTO obtenerDatosBasicos(@PathVariable("id") Integer id);
}