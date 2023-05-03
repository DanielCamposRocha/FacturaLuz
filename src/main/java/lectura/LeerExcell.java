package lectura;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;


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
                    LocalDateTime fechL=pasarLecturaPrecio(fecha);
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

    }

    private static LocalDateTime pasarLecturaPrecio(String fecha) {
        int horita= Integer.parseInt(fecha.split("T")[1].substring(0,2));
        int dia=Integer.parseInt(fecha.split("-")[2].substring(0,2));
        int mes=Integer.parseInt(fecha.split("-")[1]);
        int anho=Integer.parseInt(fecha.split("-")[0]);
        return LocalDateTime.of(anho,mes,dia,horita,0);
    }

    public static LocalDateTime pasarLectura(String fecha,Double hora){
        int horita= (int) Math.round(hora-1);
        int dia=Integer.parseInt(fecha.split("/")[0]);
        int mes=Integer.parseInt(fecha.split("/")[1]);
        int anho=Integer.parseInt(fecha.split("/")[2]);
        return LocalDateTime.of(anho,mes,dia,horita,0);
    }
}
