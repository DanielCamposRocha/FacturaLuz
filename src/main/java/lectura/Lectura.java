package lectura;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static Ejecutable.Ejecutable.listaLecturas;

public record Lectura(String numContador, LocalDateTime fechaContador, Double consumo, String metodoObtencion) {

    @Override
    public String toString() {
        return numContador + " " + fechaContador + " " + consumo + " " + metodoObtencion;
    }

    public static ArrayList<Lectura> lecturasEntrefechas(LocalDateTime inicio, LocalDateTime Tfinal) {
        ArrayList<Lectura> listaTemporalLecturas = new ArrayList<>();
        for (Lectura lectura : listaLecturas
        ) {
            if (lectura.fechaContador().isAfter(inicio) & lectura.fechaContador().isBefore(Tfinal))
                listaTemporalLecturas.add(lectura);
        }
        return listaTemporalLecturas;
    }
}
