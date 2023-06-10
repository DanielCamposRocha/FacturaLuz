package Ejecutable;

import Costes.CalculosGastos;
import archivos.EscribirCsv;
import consumos.ConsumosMedios;
import lectura.Lectura;
import archivos.LeerExcell;
import precios.Precio;
import utilidades.Menu;
import utilidades.Utilidades;

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
        Menu m=new Menu("Menu Principal",new String[] {"1.-Importar nuevos Datos ","2.-Cargar Listas de precios y lecturas precias","3.-Medias energia consumida por años", "4.-Medias energia consumida por dias de la semana","5.-Medias energia consumida por meses","6.-Gasto energia activa entre dos fechas","0.-Sair"},"0123456",Menu.Direccion.VERTICAL);

        do {
            op=m.getOption();
            switch (op) {
                case '1' -> {
                    int carga;
                    do {
                        carga = Utilidades.pedirInt("Para importar datos de lecturas contador pulse(1), para importar datos de precios pulse (2) para salir (0)");
                    } while (carga != 1 & carga != 2 & carga != 0);
                    if (carga == 1) {
                        LeerExcell.leerExcell(Utilidades.pedirString("Introduzca nombre del archivo con su extension: "));
                        EscribirCsv.EscribirLecturas();
                        ConsumosMedios.calcularMedia();
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
                case '3' -> ConsumosMedios.mediaFiltradaAnho2();
                case '4' -> ConsumosMedios.mediaFiltradaSemana2();
                case '5' -> ConsumosMedios.mediaFiltradaMes();
                case '6' -> CalculosGastos.gastosEntrefechas();
            }

        } while(op!='0');
    }

}
