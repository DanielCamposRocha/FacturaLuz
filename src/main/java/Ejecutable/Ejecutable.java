package Ejecutable;

import Costes.CalculosConsumos;
import Costes.GastoEnergia;
import archivos.EscribirCsv;
import consumos.ConsumosHorarios;
import grafico.GeneraGrafico;
import lectura.Lectura;
import archivos.LeerExcell;
import precios.Precio;
import utilidades.Menu;
import utilidades.Utilidades;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class Ejecutable {

    public static ArrayList<Lectura> listaLecturas;
    public static ArrayList<Precio> listaPrecios;

    public static void main(String[] args) {
        listaLecturas = new ArrayList<>();
        listaPrecios=new ArrayList<>();
        menu();

    }

    private static void menu() {
        char op;
        Menu m=new Menu("Menu Principal",new String[] {"1.-Importar nuevos Datos ","2.-Cargar Listas de precios y lecturas precias","3.-Medias por años", "4.-Medias por dias de la semana","5.-Medias por meses","6.-Gastos anuales","0.-Sair"},"0123456",Menu.Direccion.VERTICAL);

        do {
            op=m.getOption();
            switch (op) {
                case '1' -> {
                    int carga;
                    do {
                        carga = Utilidades.pedirInt("Para importar datos de lecturas contador pulse(1), para importar datos de precios pulse (2) para salir (0)");
                    } while (carga != 1 & carga != 2 & carga != 0);
                    if (carga == 1) {
                        LeerExcell.leerExcell("Lecturas264.xlsx");
                        EscribirCsv.EscribirLecturas();
                        calcularMedia();
                    }
                    if (carga == 2) {
                        LeerExcell.leerExcellPrecios("PreciosImportadosPVPC.xlsx");
                        EscribirCsv.EscribirPrecios();
                    }
                }
                case '2' -> {
                    LeerExcell.leerLecturas("ES0022000004433403RW1P.csv");
                    LeerExcell.leerPrecios("precios.csv");
                }
                case '3' -> mediaFiltradaAnho2();
                case '4' -> mediaFiltradaSemana2();
                case '5' -> mediaFiltradaMes();
                case '6' -> gastosPoranho();
            }

        } while(op!='0');
    }

    private static void gastosPoranho() {
        int anho=Utilidades.pedirInt("Introduzca año");
        LocalDateTime inicio=LocalDateTime.of(anho-1,12,31,23,0);
        LocalDateTime finalT=LocalDateTime.of(anho+1,1,1,0,0);
        ArrayList<GastoEnergia> gastosEnergia= CalculosConsumos.gastoPvpc(inicio,finalT);
        double coste=0;
        for (GastoEnergia gasto:gastosEnergia
             ) {
            coste+=gasto.getEnergia()* (gasto.getPrecio()/1000);
        }
        System.out.println("El coste de la energia de "+anho+" es "+coste+" €");
    }

    private static void mediaFiltradaMes() {
        ArrayList<Lectura> listaMes=new ArrayList<>();
        double consumoMes;

        for (int i=1;i<=12;i++){
            consumoMes=0;
            listaMes.clear();
            for (Lectura lectura:listaLecturas
            ) {
                if(lectura.getFechaContador().getMonthValue()==i){
                    listaMes.add(lectura);
                    consumoMes+=lectura.getConsumo();
                    }
            }
            System.out.println("Por lo que el consumo medio en "+ Month.of(i)+" ha sido de: "+(consumoMes/ listaMes.size())+" kw/h");
            mediaFiltradaHora(listaMes, String.valueOf(Month.of(i)));
            System.out.println();
        }
    }


    private static void mediaFiltradaHora(ArrayList<Lectura> listaLecturas,String nombregrafico) {
        ArrayList <ConsumosHorarios> hora=new ArrayList<>();
        for(int i=0;i<24;i++){

            double consumo=0;
            int contador=0;
            for (Lectura lectura : listaLecturas) {
                if(lectura.getFechaContador().getHour()==i){
                    consumo+= lectura.getConsumo();
                    contador++;

                }
            }
            System.out.println("Por lo que el consumo medio en la hora"+i+" ha sido de: "+(consumo/ contador)+" kw/h");
            hora.add(new ConsumosHorarios((consumo/contador), i));
        }
        GeneraGrafico.Creagrafico(hora,nombregrafico);
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
            mediaFiltradaHora(listaAnho,String.valueOf(i));
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
            mediaFiltradaHora(listaDiaSemana, String.valueOf(DayOfWeek.of(i)));
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
