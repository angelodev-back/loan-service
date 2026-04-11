package pe.edu.idat.loan_service.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class MultaCalculator {

    @Value("${prestamo.multa.diaria:2.00}")
    private BigDecimal multaDiaria;

    public BigDecimal calcularMulta(LocalDate fechaLimite, LocalDate fechaDevolucion) {
        if (fechaDevolucion == null || !fechaDevolucion.isAfter(fechaLimite)) {
            return BigDecimal.ZERO;
        }
        long diasRetraso = ChronoUnit.DAYS.between(fechaLimite, fechaDevolucion);
        return multaDiaria.multiply(BigDecimal.valueOf(diasRetraso))
                .setScale(2, RoundingMode.HALF_UP);
    }
}