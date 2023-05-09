package precios;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static Ejecutable.Ejecutable.listaPrecios;

public record Precio(LocalDateTime fechaPrecio, Double precio, Double geoid) {

    @Override
    public String toString() {
        return "En fecha: " + fechaPrecio + ", el precio es " + precio + ", en sistema " + geoid;
    }

    public static ArrayList<Precio> preciosEntrefechas(LocalDateTime inicio, LocalDateTime Tfinal) {
        ArrayList<Precio> listaTemporalPrecios = new ArrayList<>();

        for (Precio precio : listaPrecios
        ) {
            if (precio.fechaPrecio().isAfter(inicio) & precio.fechaPrecio().isBefore(Tfinal))
                listaTemporalPrecios.add(precio);
        }
        return listaTemporalPrecios;
    }
}
