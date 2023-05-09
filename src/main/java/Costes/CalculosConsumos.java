package Costes;

import lectura.Lectura;
import precios.Precio;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class CalculosConsumos {
    public static ArrayList<GastoEnergia> gastoPvpc(LocalDateTime inicio, LocalDateTime Tfinal){
        ArrayList<GastoEnergia> listaCoste=new ArrayList<>();
        ArrayList<Lectura>listaTemporalLecturas=Lectura.lecturasEntrefechas(inicio, Tfinal);
        ArrayList<Precio>listaTemporalPrecios=Precio.preciosEntrefechas(inicio, Tfinal);
        for(int i=0; i<listaTemporalLecturas.size();i++){
            if(listaTemporalLecturas.get(i).fechaContador().equals(listaTemporalPrecios.get(i).fechaPrecio())){
                listaCoste.add(new GastoEnergia(listaTemporalLecturas.get(i).consumo(),listaTemporalPrecios.get(i).precio(),listaTemporalLecturas.get(i).fechaContador()));
            }
        }
        return listaCoste;
    }




}
