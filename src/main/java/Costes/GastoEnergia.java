package Costes;

import java.time.LocalDateTime;

public record GastoEnergia(double energia, double precio, LocalDateTime fechaGasto) {

    @Override
    public String toString() {
        return "energia: " + energia + ", precio: " + precio + ", fechaGasto: " + fechaGasto;
    }


}
