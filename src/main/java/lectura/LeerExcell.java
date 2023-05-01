package lectura;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class LeerExcell {
    static ArrayList<Lectura> listaLecturas;
    public static void main(String[] args) {
        listaLecturas=new ArrayList<>();
        leerExcell();
        calcularMedia();
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
            FileInputStream fis=new FileInputStream("consumptions.xlsx");
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
                    Double lectura=Double.parseDouble(consumo.split(",")[0]+"."+consumo.split(",")[1]);
                    listaLecturas.add(new Lectura(numContador,fecha,hora,lectura,metodoObtencion));
                             }
                contadorFilas++;
            }
            libro.close();
            fis.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }
        System.out.println(listaLecturas);
    }
}