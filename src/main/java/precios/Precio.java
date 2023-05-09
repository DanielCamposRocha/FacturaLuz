package precios;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static Ejecutable.Ejecutable.listaPrecios;

public class Precio {
    private final LocalDateTime fechaPrecio;
    private final Double precio;
    private final Double geoid;

    public Precio(LocalDateTime fechaPrecio, Double precio, Double geoid) {
        this.fechaPrecio = fechaPrecio;
        this.precio = precio;
        this.geoid = geoid;
    }

    public LocalDateTime getFechaPrecio() {
        return fechaPrecio;
    }

    public Double getPrecio() {
        return precio;
    }

    public Double getGeoid() {
        return geoid;
    }

    @Override
    public String toString() {
        return "En fecha: " + fechaPrecio +", el precio es " + precio +", en sistema " + geoid ;
    }

    public static ArrayList<Precio> preciosEntrefechas(LocalDateTime inicio, LocalDateTime Tfinal){
        ArrayList<Precio>listaTemporalPrecios=new ArrayList<>();

        for (Precio precio:listaPrecios
        ) {
            if(precio.getFechaPrecio().isAfter(inicio) & precio.getFechaPrecio().isBefore(Tfinal)) listaTemporalPrecios.add(precio);
        }
        return  listaTemporalPrecios;
    }
}
