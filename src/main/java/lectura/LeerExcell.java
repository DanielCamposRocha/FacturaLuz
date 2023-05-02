package lectura;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class LeerExcell {
    static ArrayList<Lectura> listaLecturas;
    public static void main(String[] args) {
        listaLecturas=new ArrayList<>();
        leerExcell();
        calcularMedia();
        mediaFiltradaAnho();
        mediaFiltradaHora();
    }

    private static void mediaFiltradaHora() {

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
        System.out.println("Por lo que el consumo medio en 2022 ha sido de: "+(consumo22/ lista22.size())+" kw/h");
        System.out.println("Por lo que el consumo medio en 2023 ha sido de: "+(consumo23/ lista23.size())+" kw/h");
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

    public static void leerExcell(){

        try {
            FileInputStream fis=new FileInputStream("Lecturas264.xlsx");
            XSSFWorkbook libro = new XSSFWorkbook(fis);
            XSSFSheet hoja = libro.getSheetAt(0);
            int contadorFilas=0;
            for (Row fila : hoja) {
                if(contadorFilas==0){
                }else{
                    String numContador=fila.getCell(0).getStringCellValue();
                    String fecha=fila.getCell(1).getStringCellValue();
                    Double hora=fila.getCell(2).getNumericCellValue();
                    String consumo=fila.getCell(3).getStringCellValue();
                    String metodoObtencion=fila.getCell(4).getStringCellValue();
                    LocalDateTime fechL=pasarLectura(fecha,hora);
                    Double lectura=Double.parseDouble(consumo.split(",")[0]+"."+consumo.split(",")[1]);
                    listaLecturas.add(new Lectura(numContador,fechL,lectura,metodoObtencion));
                             }
                contadorFilas++;
            }
            libro.close();
            fis.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }

    }

    public static LocalDateTime pasarLectura(String fecha,Double hora){
        int horita= (int) Math.round(hora-1);
        int dia=Integer.parseInt(fecha.split("/")[0]);
        int mes=Integer.parseInt(fecha.split("/")[1]);
        int anho=Integer.parseInt(fecha.split("/")[2]);
        return LocalDateTime.of(anho,mes,dia,horita,0);
    }
}
