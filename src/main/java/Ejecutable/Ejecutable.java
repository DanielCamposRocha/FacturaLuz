package Ejecutable;

import escritura.EscribirCsv;
import lectura.Lectura;
import lectura.LeerExcell;
import lectura.Precio;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ejecutable {

    public static ArrayList<Lectura> listaLecturas;
    public static ArrayList<Precio> listaPrecios;

    public static void main(String[] args) {
        listaLecturas = new ArrayList<>();
        listaPrecios=new ArrayList<>();
       LeerExcell.leerExcell("Lecturas264.xlsx");
        /*calcularMedia();
        mediaFiltradaAnho2();
        mediaFiltradaSemana2();*/
        LeerExcell.leerExcellPrecios("PreciosImportadosPVPC.xlsx");
        EscribirCsv.EscribirPrecios();
        EscribirCsv.EscribirLecturas();
        System.out.println(listaLecturas);
    }


    private static void mediaFiltradaHora(ArrayList<Lectura> listaLecturas) {

        for(int i=0;i<24;i++){
            ArrayList <Lectura> hora=new ArrayList<>();
            double consumo=0;
            int contador=0;
            for (Lectura lectura : listaLecturas) {
                if(lectura.getFechaContador().getHour()==i){
                    consumo+= lectura.getConsumo();
                    contador++;
                }
            }
            System.out.println("Por lo que el consumo medio en la hora"+i+" ha sido de: "+(consumo/ contador)+" kw/h");
        }
    }


    private static void mediaFiltradaAnho2(){
        ArrayList<Lectura> listaAnho=new ArrayList<>();
        double consumoAnho;
        int anhoInicial=listaLecturas.get(0).getFechaContador().getYear();
        int anhoFinal= LocalDateTime.now().getYear();
        for (int i=anhoInicial;i<=anhoFinal;i++){
            consumoAnho=0;
            listaAnho.clear();
            for (Lectura lectura:listaLecturas
            ) {
                if(lectura.getFechaContador().getYear()==i){
                listaAnho.add(lectura);
                consumoAnho=consumoAnho+lectura.getConsumo();
                }
            }
            System.out.println("Por lo que el consumo medio en "+i+" ha sido de: "+(consumoAnho/ listaAnho.size())+" kw/h");
            mediaFiltradaHora(listaAnho);
            System.out.println();
        }
    }
    private static void mediaFiltradaSemana2(){
        ArrayList<Lectura> listaDiaSemana=new ArrayList<>();
        double consumoDiaSemana;
        for(int i=1;i<8;i++){
            listaDiaSemana.clear();
            consumoDiaSemana=0;
            for (Lectura lectura:listaLecturas
            ) {
                if (lectura.getFechaContador().getDayOfWeek().getValue() == i) {
                    listaDiaSemana.add(lectura);
                    consumoDiaSemana += lectura.getConsumo();
                }
            }
            System.out.println("Por lo que el consumo medio en "+DayOfWeek.of(i)+" ha sido de: "+(consumoDiaSemana/ listaDiaSemana.size())+" kw/h");
            mediaFiltradaHora(listaDiaSemana);
            System.out.println();
        }
    }
    private static void calcularMedia() {
        double consumo=0;
        for (Lectura lectura:listaLecturas
        ) {
            consumo=consumo+lectura.getConsumo();
        }
        System.out.println("El consumo total ha sido: "+consumo+" kw/h a lo largo de "+listaLecturas.size()+" horas");
        System.out.println("Por lo que el consumo medio ha sido de: "+(consumo/ listaLecturas.size())+" kw/h");
    }
}
