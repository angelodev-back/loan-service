package pe.edu.idat.loan_service.dto.response;

import java.time.LocalDate;

public class ReservaResponseDTO {
	private Integer idReserva;
    private Integer idUsuario;
    private Integer idLibro;
    private String nombreUsuario;
    private String tituloLibro;
    private LocalDate fechaReserva;
    private String estado;
    
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
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getTituloLibro() {
		return tituloLibro;
	}
	public void setTituloLibro(String tituloLibro) {
		this.tituloLibro = tituloLibro;
	}
	public LocalDate getMaybeReserva(LocalDate fechaReserva) {
		return fechaReserva;
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
