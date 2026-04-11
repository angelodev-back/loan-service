package pe.edu.idat.loan_service.dto.request;

import jakarta.validation.constraints.NotNull;

public class PrestamoRequestDTO {

	@NotNull(message = "El ID del usuario es obligatorio")
    private Integer idUsuario;

    @NotNull(message = "El ID del libro es obligatorio")
    private Integer idLibro;

    public PrestamoRequestDTO() {}

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
