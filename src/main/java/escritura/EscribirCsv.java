package escritura;

import lectura.Lectura;
import lectura.Precio;

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
               escribe.write(precio.getFechaPrecio()+","+precio.getPrecio()+","+precio.getGeoid()+"\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void EscribirLecturas(){
        String nombre=listaLecturas.get(0).getNumContador();
        try(BufferedWriter escribe = new BufferedWriter(new FileWriter(nombre+".csv"))){
            for (Lectura lectura:listaLecturas
            ) {
                escribe.write(lectura.getFechaContador()+","+String.valueOf(lectura.getConsumo())+","+lectura.getMetodoObtencion()+"\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
