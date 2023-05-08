package archivos;


import lectura.Lectura;
import precios.Precio;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;


import static Ejecutable.Ejecutable.listaLecturas;
import static Ejecutable.Ejecutable.listaPrecios;


public class LeerExcell {



    public static void leerExcell(String archivo){

        try {
            FileInputStream fis=new FileInputStream(archivo);
            XSSFWorkbook libro = new XSSFWorkbook(fis);
            XSSFSheet hoja = libro.getSheetAt(0);
            int contadorFilas=0;
            for (Row fila : hoja) {
                if(contadorFilas!=0){
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

    public static void leerExcellPrecios(String archivo){

        try {
            FileInputStream fis=new FileInputStream(archivo);
            XSSFWorkbook libro = new XSSFWorkbook(fis);
            XSSFSheet hoja = libro.getSheetAt(0);
            int contadorFilas=0;
            for (Row fila : hoja) {
                if(contadorFilas==0|contadorFilas==1){
                }else{
                    //ni idea para que sirve este valor podriamos obivarlo queda listo por si hace falta
                    //double id=fila.getCell(0).getNumericCellValue();
                    //nombre del tipo de tarifa por ahora lo obviamos
                    //String nombre=fila.getCell(1).getStringCellValue();
                    double geoid=fila.getCell(2).getNumericCellValue();
                    //geoid y geoname indican el sistema electrico uno en double otro en string
                    //String geoname=fila.getCell(3).getStringCellValue();
                    double precio=fila.getCell(4).getNumericCellValue();
                    String fecha=fila.getCell(5).getStringCellValue();
                    LocalDateTime fechL=pasarLectura(fecha);
                    listaPrecios.add(new Precio(fechL,precio,geoid));
                }
                contadorFilas++;
            }
            libro.close();
            fis.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }
        System.out.println("Archivo leido correctamente");
    }
    public static void leerPrecios(String archivoPrecios) {
        ArrayList<Precio> listatemporaPrecios=new ArrayList<>();
        try(BufferedReader lee=new BufferedReader(new FileReader(archivoPrecios))){
            String linea="";
            while((linea=lee.readLine())!=null){
                String fecha=linea.split(",")[0];
                String precioS=linea.split(",")[1];
                String sistema=linea.split(",")[2];
                LocalDateTime fechaT=pasarLectura(fecha);
                double precio=Double.parseDouble(precioS);
                double sistemaD=Double.parseDouble(sistema);
                listatemporaPrecios.add(new Precio(fechaT,precio,sistemaD));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        listaPrecios=listatemporaPrecios;
        System.out.println("Precios leidos correctamente");
    }

    public static void leerLecturas(String archivoLecturas){
        String contador=archivoLecturas.substring(0,archivoLecturas.length()-4);
        System.out.println(contador);
        ArrayList<Lectura> listatemporaLecturas=new ArrayList<>();
        try(BufferedReader lee=new BufferedReader(new FileReader(archivoLecturas))){
            String linea="";
            while((linea=lee.readLine())!=null){
                String fecha=linea.split(",")[0];
                String lecturaS=linea.split(",")[1];
                String metodo=linea.split(",")[2];
                LocalDateTime fechaT=pasarLectura(fecha);
                double lecturaD=Double.parseDouble(lecturaS);

                listatemporaLecturas.add(new Lectura(contador,fechaT,lecturaD,metodo));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        listaLecturas=listatemporaLecturas;
        System.out.println("lecturas leidas correctamente");
    }
    private static LocalDateTime pasarLectura(String fecha) {
        int horita= Integer.parseInt(fecha.split("T")[1].substring(0,2));
        int dia=Integer.parseInt(fecha.split("-")[2].substring(0,2));
        int mes=Integer.parseInt(fecha.split("-")[1]);
        int anho=Integer.parseInt(fecha.split("-")[0]);
        return LocalDateTime.of(anho,mes,dia,horita,0);
    }

    //hay que restar una hora por que en el formato del contador llega hasta las 24
    public static LocalDateTime pasarLectura(String fecha,Double hora){

        int horita= (int) Math.round(hora-1);
        int dia=Integer.parseInt(fecha.split("/")[0]);
        int mes=Integer.parseInt(fecha.split("/")[1]);
        int anho=Integer.parseInt(fecha.split("/")[2]);
        return LocalDateTime.of(anho,mes,dia,horita,0);
    }
}
