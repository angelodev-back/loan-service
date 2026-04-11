package pe.edu.idat.loan_service.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PrestamoResponseDTO {
	private Integer idPrestamo;
    private Integer idUsuario;
    private Integer idLibro;
    private String nombreUsuario;
    private String tituloLibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaLimite;
    private String estado;
    private BigDecimal multa;
    
	public Integer getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(Integer idPrestamo) {
		this.idPrestamo = idPrestamo;
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
	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(LocalDate fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	public LocalDate getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(LocalDate fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public BigDecimal getMulta() {
		return multa;
	}
	public void setMulta(BigDecimal multa) {
		this.multa = multa;
	}  
}