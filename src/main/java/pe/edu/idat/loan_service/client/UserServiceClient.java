package pe.edu.idat.loan_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.idat.loan_service.dto.external.UsuarioBasicoDTO;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceClient {

    @GetMapping("/api/internal/usuarios/{id}/validar")
    Boolean validarUsuarioActivo(@PathVariable("id") Integer id);

    @GetMapping("/api/internal/usuarios/{id}/existe")
    Boolean existeUsuario(@PathVariable("id") Integer id);

    @GetMapping("/api/internal/usuarios/{id}/basico")
    UsuarioBasicoDTO obtenerDatosBasicos(@PathVariable("id") Integer id);
}