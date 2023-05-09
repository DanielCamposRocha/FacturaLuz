package consumos;

public class ConsumosHorarios {
    private Double consumoEA;
    private int hora;

    public ConsumosHorarios(Double consumoEA, int hora) {
        this.consumoEA = consumoEA;
        this.hora = hora;
    }

    public Double getConsumoEA() {
        return consumoEA;
    }

    public void setConsumoEA(Double consumoEA) {
        this.consumoEA = consumoEA;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "consumo energia activa: " + consumoEA +", hora: " + hora ;
    }
}
