package pe.edu.idat.loan_service.dto.to;

import jakarta.validation.constraints.NotNull;

public class ReservaRequestDTO {

    @NotNull(message = "El idUsuario es obligatorio")
    private Integer idUsuario;

    @NotNull(message = "El idLibro es obligatorio")
    private Integer idLibro;

    public ReservaRequestDTO() {
    }

    public ReservaRequestDTO(Integer idUsuario, Integer idLibro) {
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }
}