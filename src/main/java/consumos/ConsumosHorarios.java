package consumos;

public class ConsumosHorarios {
    private Double consumo;
    private int hora;

    public ConsumosHorarios(Double consumo, int hora) {
        this.consumo = consumo;
        this.hora = hora;
    }

    public Double getConsumo() {
        return consumo;
    }

    public void setConsumo(Double consumo) {
        this.consumo = consumo;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "consumo: " + consumo +", hora: " + hora ;
    }
}
