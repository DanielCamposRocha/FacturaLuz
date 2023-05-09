package consumos;

public record ConsumosHorarios(Double consumoEA, int hora) {


    @Override
    public String toString() {
        return "consumo energia activa: " + consumoEA + ", hora: " + hora;
    }
}
