package Costes;

import consumos.ConsumosMedios;
import lectura.Lectura;
import precios.Precio;
import utilidades.Utilidades;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class CalculosGastos {
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

    //hay diferencias de coste con la cnmc por los redondeos, al grafico se envia la adecuada al cnmc
    public static void gastosEntrefechas() {
        int c;
        LocalDateTime inicio,inicio2,finalT,finalT2;
        do{
            c=0;
            inicio=LocalDateTime.of(Utilidades.pedirInt("Introduzca año inicio"),Utilidades.pedirInt("Introduzca mes inicio"),Utilidades.pedirInt("Introduzca dia inicio"),Utilidades.pedirInt("Introduzca hora inicio"),0);
            inicio2=inicio.minusHours(1);
            finalT=LocalDateTime.of(Utilidades.pedirInt("Introduzca año final"),Utilidades.pedirInt("Introduzca mes final"),Utilidades.pedirInt("Introduzca dia final"),Utilidades.pedirInt("Introduzca hora final"),0);
            finalT2=finalT.plusHours(1);
            if(inicio.isAfter(finalT)){
                c++;
                System.out.println("La facha final debe ser mayor que la de inicio");
                System.out.println("reintroduzca datos correctamente por favor");
            }
        }while (c!=0);
        ArrayList<GastoEnergia> gastosEnergia= CalculosGastos.gastoPvpc(inicio2,finalT2);
        double coste=0;
        for (GastoEnergia gasto:gastosEnergia
        ) {
            coste+=gasto.energia()* (gasto.precio()/1000);
        }
        double cosR=coste*100;
        double costeRedondeo=Math.round(cosR);
        System.out.println("El coste de la energia entre "+inicio+" y "+finalT+" es "+coste+" €");
        ConsumosMedios.mediaFiltradaHora(gastosEnergia,inicio,finalT,costeRedondeo/100);
    }




}
