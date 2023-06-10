package consumos;

import Costes.GastoEnergia;
import grafico.GeneraGrafico;
import lectura.Lectura;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import static Ejecutable.Ejecutable.listaLecturas;

public class ConsumosMedios {
    public static void mediaFiltradaMes() {
        ArrayList<Lectura> listaMes=new ArrayList<>();
        double consumoMes;

        for (int i=1;i<=12;i++){
            consumoMes=0;
            listaMes.clear();
            for (Lectura lectura:listaLecturas
            ) {
                if(lectura.fechaContador().getMonthValue()==i){
                    listaMes.add(lectura);
                    consumoMes+=lectura.consumo();
                }
            }
            System.out.println("Por lo que el consumo medio en "+ Month.of(i)+" ha sido de: "+(consumoMes/ listaMes.size())+" kw/h");
            mediaFiltradaHora(listaMes, String.valueOf(Month.of(i)));
            System.out.println();
        }
    }


    public static void mediaFiltradaHora(ArrayList<GastoEnergia> listaGastos, LocalDateTime inicio, LocalDateTime finalT, double gastoEnergia) {
        ArrayList <ConsumoEuros> hora=new ArrayList<>();
        for(int i=0;i<24;i++){
            double coste=0;
            double consumo=0;
            int contador=0;
            for (GastoEnergia gasto : listaGastos) {
                if(gasto.fechaGasto().getHour()==i){
                    consumo+= gasto.energia();
                    coste+=gasto.energia()*(gasto.precio()/1000);
                    contador++;

                }
            }
            System.out.println("Por lo que el consumo medio en la hora"+i+" ha sido de: "+(coste/ contador)+" €/h");
            hora.add(new ConsumoEuros((coste/contador), i,(consumo/contador)));
        }
        GeneraGrafico.graficoCosteEnergia(hora,inicio,finalT,gastoEnergia);
    }
    private static void mediaFiltradaHora(ArrayList<Lectura> listaLecturas,String nombregrafico) {
        ArrayList <ConsumosHorarios> hora=new ArrayList<>();
        for(int i=0;i<24;i++){

            double consumo=0;
            int contador=0;
            for (Lectura lectura : listaLecturas) {
                if(lectura.fechaContador().getHour()==i){
                    consumo+= lectura.consumo();
                    contador++;

                }
            }
            System.out.println("Por lo que el consumo medio en la hora"+i+" ha sido de: "+(consumo/ contador)+" kw/h");
            hora.add(new ConsumosHorarios((consumo/contador), i));
        }
        GeneraGrafico.Creagrafico(hora,nombregrafico);
    }

    public static void mediaFiltradaAnho2(){
        ArrayList<Lectura> listaAnho=new ArrayList<>();
        double consumoAnho;
        int anhoInicial=listaLecturas.get(0).fechaContador().getYear();
        int anhoFinal= LocalDateTime.now().getYear();
        for (int i=anhoInicial;i<=anhoFinal;i++){
            consumoAnho=0;
            listaAnho.clear();
            for (Lectura lectura:listaLecturas
            ) {
                if(lectura.fechaContador().getYear()==i){
                    listaAnho.add(lectura);
                    consumoAnho=consumoAnho+lectura.consumo();
                }
            }
            System.out.println("Por lo que el consumo medio en "+i+" ha sido de: "+(consumoAnho/ listaAnho.size())+" kw/h");
            mediaFiltradaHora(listaAnho,String.valueOf(i));
            System.out.println();
        }
    }
    public static void mediaFiltradaSemana2(){
        ArrayList<Lectura> listaDiaSemana=new ArrayList<>();
        double consumoDiaSemana;
        for(int i=1;i<8;i++){
            listaDiaSemana.clear();
            consumoDiaSemana=0;
            for (Lectura lectura:listaLecturas
            ) {
                if (lectura.fechaContador().getDayOfWeek().getValue() == i) {
                    listaDiaSemana.add(lectura);
                    consumoDiaSemana += lectura.consumo();
                }
            }
            System.out.println("Por lo que el consumo medio en "+ DayOfWeek.of(i)+" ha sido de: "+(consumoDiaSemana/ listaDiaSemana.size())+" kw/h");
            mediaFiltradaHora(listaDiaSemana, String.valueOf(DayOfWeek.of(i)));
            System.out.println();
        }
    }
    public static void calcularMedia() {
        double consumo=0;
        for (Lectura lectura:listaLecturas
        ) {
            consumo=consumo+lectura.consumo();
        }
        System.out.println("El consumo total ha sido: "+consumo+" kw/h a lo largo de "+listaLecturas.size()+" horas");
        System.out.println("Por lo que el consumo medio ha sido de: "+(consumo/ listaLecturas.size())+" kw/h");
    }
}
