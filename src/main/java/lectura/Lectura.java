package lectura;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static Ejecutable.Ejecutable.listaLecturas;

public class Lectura {
    private final String numContador;
    private final LocalDateTime fechaContador;
    private final Double consumo;
    private final String metodoObtencion;

    public Lectura(String numContador, LocalDateTime fechaContador, Double consumo, String metodoObtencion) {
        this.numContador = numContador;
        this.fechaContador = fechaContador;
        this.consumo = consumo;
        this.metodoObtencion = metodoObtencion;
    }

    public String getNumContador() {
        return numContador;
    }


    public LocalDateTime getFechaContador() {
        return fechaContador;
    }

    public Double getConsumo() {
        return consumo;
    }

    public String getMetodoObtencion() {
        return metodoObtencion;
    }

    @Override
    public String toString() {
        return numContador +" "+fechaContador + " " + consumo +" "+ metodoObtencion ;
    }

    public static ArrayList<Lectura> lecturasEntrefechas(LocalDateTime inicio, LocalDateTime Tfinal){
        ArrayList<Lectura>listaTemporalLecturas=new ArrayList<>();
        for (Lectura lectura:listaLecturas
        ) {
            if(lectura.getFechaContador().isAfter(inicio) & lectura.getFechaContador().isBefore(Tfinal)) listaTemporalLecturas.add(lectura);
        }
        return listaTemporalLecturas;
    }
}
