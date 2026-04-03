package pe.edu.idat.loan_service.dto.to;

import java.time.LocalDate;

public class ReservaResponseDTO {

    private Integer idReserva;
    private Integer idUsuario;
    private Integer idLibro;
    private LocalDate fechaReserva;
    private String estado;

    public ReservaResponseDTO() {
    }

    public ReservaResponseDTO(Integer idReserva, Integer idUsuario, Integer idLibro, LocalDate fechaReserva, String estado) {
        this.idReserva = idReserva;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
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

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}