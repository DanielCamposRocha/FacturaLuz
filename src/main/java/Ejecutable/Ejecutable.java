package Ejecutable;

import lectura.Lectura;
import lectura.LeerExcell;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class Ejecutable {

    public static ArrayList<Lectura> listaLecturas;

    public static void main(String[] args) {
        listaLecturas = new ArrayList<>();
        LeerExcell.leerExcell("Lecturas264.xlsx");
        calcularMedia();
        mediaFiltradaAnho();
        mediaFiltradaSemana();
    }
    private static void mediaFiltradaSemana() {
        ArrayList <Lectura> listaLunes=new ArrayList<>();
        ArrayList <Lectura> listaMartes=new ArrayList<>();
        ArrayList <Lectura> listaMiercoles=new ArrayList<>();
        ArrayList <Lectura> listaJueves=new ArrayList<>();
        ArrayList <Lectura> listaViernes=new ArrayList<>();
        ArrayList <Lectura> listaSabado=new ArrayList<>();
        ArrayList <Lectura> listaDomingo=new ArrayList<>();
        double consumoLunes=0;
        double consumoMartes=0;
        double consumoMiercoles=0;
        double consumoJueves=0;
        double consumoViernes=0;
        double consumoSabado=0;
        double consumoDomingo=0;
        for (Lectura lectura:listaLecturas
        ) {
            if(lectura.getFechaContador().getDayOfWeek()== DayOfWeek.MONDAY){
                listaLunes.add(lectura);
                consumoLunes+=lectura.getConsumo();}
            if(lectura.getFechaContador().getDayOfWeek()==DayOfWeek.TUESDAY){
                listaMartes.add(lectura);
                consumoMartes+=lectura.getConsumo();}
            if(lectura.getFechaContador().getDayOfWeek()==DayOfWeek.WEDNESDAY){
                listaMiercoles.add(lectura);
                consumoMiercoles+=lectura.getConsumo();}
            if(lectura.getFechaContador().getDayOfWeek()==DayOfWeek.THURSDAY){
                listaJueves.add(lectura);
                consumoJueves+=lectura.getConsumo();}
            if(lectura.getFechaContador().getDayOfWeek()==DayOfWeek.FRIDAY){
                listaViernes.add(lectura);
                consumoViernes+=lectura.getConsumo();}
            if(lectura.getFechaContador().getDayOfWeek()==DayOfWeek.SATURDAY){
                listaSabado.add(lectura);
                consumoSabado+=lectura.getConsumo();}
            if(lectura.getFechaContador().getDayOfWeek()==DayOfWeek.SUNDAY){
                listaDomingo.add(lectura);
                consumoDomingo+=lectura.getConsumo();}
        }
        System.out.println();
        System.out.println("Por lo que el consumo medio en Lunes ha sido de: "+(consumoLunes/ listaLunes.size())+" kw/h");
        mediaFiltradaHora(listaLunes);
        System.out.println();
        System.out.println("Por lo que el consumo medio en Martes ha sido de: "+(consumoMartes/ listaMartes.size())+" kw/h");
        mediaFiltradaHora(listaMartes);
        System.out.println();
        System.out.println("Por lo que el consumo medio en Miercoles ha sido de: "+(consumoMiercoles/ listaMiercoles.size())+" kw/h");
        mediaFiltradaHora(listaMiercoles);
        System.out.println();
        System.out.println("Por lo que el consumo medio en Jueves ha sido de: "+(consumoJueves/ listaJueves.size())+" kw/h");
        mediaFiltradaHora(listaJueves);
        System.out.println();
        System.out.println("Por lo que el consumo medio en Viernes ha sido de: "+(consumoViernes/ listaViernes.size())+" kw/h");
        mediaFiltradaHora(listaViernes);
        System.out.println();
        System.out.println("Por lo que el consumo medio en Sabado ha sido de: "+(consumoSabado/ listaSabado.size())+" kw/h");
        mediaFiltradaHora(listaSabado);
        System.out.println();
        System.out.println("Por lo que el consumo medio en Domingo ha sido de: "+(consumoDomingo/ listaDomingo.size())+" kw/h");
        mediaFiltradaHora(listaDomingo);
        System.out.println();

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

    private static void mediaFiltradaAnho() {
        ArrayList <Lectura> lista21=new ArrayList<>();
        ArrayList <Lectura> lista22=new ArrayList<>();
        ArrayList <Lectura> lista23=new ArrayList<>();
        double consumo21=0;
        double consumo22=0;
        double consumo23=0;
        for (Lectura lectura:listaLecturas
        ) {
            if(lectura.getFechaContador().getYear()==2021){
                lista21.add(lectura);
                consumo21=consumo21+lectura.getConsumo();}
            if(lectura.getFechaContador().getYear()==2022){
                lista22.add(lectura);
                consumo22=consumo22+lectura.getConsumo();}
            if(lectura.getFechaContador().getYear()==2023){
                lista23.add(lectura);
                consumo23=consumo23+lectura.getConsumo();}
        }
        System.out.println("Por lo que el consumo medio en 2021 ha sido de: "+(consumo21/ lista21.size())+" kw/h");
        mediaFiltradaHora(lista21);
        System.out.println();
        System.out.println("Por lo que el consumo medio en 2022 ha sido de: "+(consumo22/ lista22.size())+" kw/h");
        mediaFiltradaHora(lista22);
        System.out.println();
        System.out.println("Por lo que el consumo medio en 2023 ha sido de: "+(consumo23/ lista23.size())+" kw/h");
        mediaFiltradaHora(lista23);
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
