package archivos;

import lectura.Lectura;
import precios.Precio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static Ejecutable.Ejecutable.listaLecturas;
import static Ejecutable.Ejecutable.listaPrecios;

public class EscribirCsv {
    public static void EscribirPrecios(){
        try(BufferedWriter escribe = new BufferedWriter(new FileWriter("precios.csv"))){
            for (Precio precio:listaPrecios
                 ) {
               escribe.write(precio.fechaPrecio()+","+precio.precio()+","+precio.geoid()+"\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Archivo guardado correctamente");
    }

    public static void EscribirLecturas(){
        String nombre=listaLecturas.get(0).numContador();
        try(BufferedWriter escribe = new BufferedWriter(new FileWriter(nombre+".csv"))){
            for (Lectura lectura:listaLecturas
            ) {
                escribe.write(lectura.fechaContador()+","+ lectura.consumo() +","+lectura.metodoObtencion()+"\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Archivo guardado correctamente");
    }
}
