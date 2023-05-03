package lectura;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;


import static Ejecutable.Ejecutable.listaLecturas;


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

    public static LocalDateTime pasarLectura(String fecha,Double hora){
        int horita= (int) Math.round(hora-1);
        int dia=Integer.parseInt(fecha.split("/")[0]);
        int mes=Integer.parseInt(fecha.split("/")[1]);
        int anho=Integer.parseInt(fecha.split("/")[2]);
        return LocalDateTime.of(anho,mes,dia,horita,0);
    }
}
