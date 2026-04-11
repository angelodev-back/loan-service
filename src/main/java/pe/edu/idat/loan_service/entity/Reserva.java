package pe.edu.idat.loan_service.entity;

import java.time.LocalDate;

import pe.edu.idat.loan_service.auditable.Auditable;
import jakarta.persistence.*;

@Entity
@Table(name = "reserva")
public class Reserva extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Integer idReserva;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "id_libro", nullable = false)
    private Integer idLibro;

    @Column(name = "fecha_reserva", nullable = false)
    private LocalDate fechaReserva;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Transient
    private String nombreUsuario;
    
    @Transient
    private String tituloLibro;

    public Reserva() {}

	public Reserva(Integer idReserva, Integer idUsuario, Integer idLibro, LocalDate fechaReserva, String estado,
			String nombreUsuario, String tituloLibro) {
		this.idReserva = idReserva;
		this.idUsuario = idUsuario;
		this.idLibro = idLibro;
		this.fechaReserva = fechaReserva;
		this.estado = estado;
		this.nombreUsuario = nombreUsuario;
		this.tituloLibro = tituloLibro;
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
}