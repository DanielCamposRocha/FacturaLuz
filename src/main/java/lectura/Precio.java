package lectura;

import java.time.LocalDateTime;

public class Precio {
    private LocalDateTime fechaPrecio;
    private Double precio;
    private Double geoid;

    public Precio(LocalDateTime fechaPrecio, Double precio, Double geoid) {
        this.fechaPrecio = fechaPrecio;
        this.precio = precio;
        this.geoid = geoid;
    }

    public LocalDateTime getFechaPrecio() {
        return fechaPrecio;
    }

    public void setFechaPrecio(LocalDateTime fechaPrecio) {
        this.fechaPrecio = fechaPrecio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getGeoid() {
        return geoid;
    }

    public void setGeoid(Double geoid) {
        this.geoid = geoid;
    }

    @Override
    public String toString() {
        return "En fecha: " + fechaPrecio +", el precio es " + precio +", en sistema " + geoid ;
    }
}
