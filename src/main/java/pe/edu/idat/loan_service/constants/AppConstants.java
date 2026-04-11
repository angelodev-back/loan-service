package pe.edu.idat.loan_service.constants;

public final class AppConstants {
    private AppConstants() {
        throw new IllegalStateException("Clase de constantes - No instanciar");
    }
    
    public static final int DIAS_PRESTAMO = 7;
    public static final double MULTA_DIARIA = 2.00;
    public static final int MAX_LIBROS_PRESTAMO = 3;
}