package pe.edu.idat.loan_service.dto.request;

import jakarta.validation.constraints.NotNull;

public class DevolucionRequestDTO {

	@NotNull(message = "El ID del préstamo es obligatorio")
    private Integer idPrestamo;

    public DevolucionRequestDTO() {}

    public Integer getIdPrestamo() { 
    	return idPrestamo; 
    }
    
    public void setIdPrestamo(Integer idPrestamo) { 
    	this.idPrestamo = idPrestamo; 
    }
}
