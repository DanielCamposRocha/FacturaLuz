package consumos;

public class ConsumoEuros {
    private Double consumo;
    private int hora;
    private  Double energia;

    public ConsumoEuros(Double consumo, int hora,Double energia) {
        this.consumo = consumo;
        this.hora = hora;
        this.energia=energia;
    }

    public Double getEnergia() {
        return energia;
    }

    public void setEnergia(Double energia) {
        this.energia = energia;
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
        return "consumo: " + consumo +",energia: "+energia+", hora: " + hora ;
    }
}
