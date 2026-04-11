package pe.edu.idat.loan_service.dto.external;

public class LibroDisponibilidadDTO {
    private Integer idLibro;
    private boolean disponible;
    private Integer stockActual;
    private boolean puedePrestar;
    
    public Integer getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(Integer idLibro) {
		this.idLibro = idLibro;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public Integer getStockActual() {
		return stockActual;
	}
	public void setStockActual(Integer stockActual) {
		this.stockActual = stockActual;
	}
	public boolean isPuedePrestar() {
		return puedePrestar;
	}
	public void setPuedePrestar(boolean puedePrestar) {
		this.puedePrestar = puedePrestar;
	}
}
