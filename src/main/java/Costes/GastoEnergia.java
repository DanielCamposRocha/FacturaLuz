package Costes;

import java.time.LocalDateTime;

public class GastoEnergia {
    private double energia;
    private  double precio;
    private LocalDateTime fechaGasto;

    public GastoEnergia(double energia, double precio, LocalDateTime fechaGasto) {
        this.energia = energia;
        this.precio = precio;
        this.fechaGasto = fechaGasto;
    }

    public double getEnergia() {
        return energia;
    }

    public void setEnergia(double energia) {
        this.energia = energia;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDateTime getFechaGasto() {
        return fechaGasto;
    }

    public void setFechaGasto(LocalDateTime fechaGasto) {
        this.fechaGasto = fechaGasto;
    }

    @Override
    public String toString() {
        return "energia: " + energia +", precio: " + precio +", fechaGasto: " + fechaGasto ;
    }


}
