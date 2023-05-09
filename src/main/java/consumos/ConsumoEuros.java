package consumos;

public class ConsumoEuros {
    private Double consumoEA;
    private int hora;
    private  Double energia;

    public ConsumoEuros(Double consumoEA, int hora, Double energia) {
        this.consumoEA = consumoEA;
        this.hora = hora;
        this.energia=energia;
    }

    public Double getEnergia() {
        return energia;
    }

    public void setEnergia(Double energia) {
        this.energia = energia;
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
        return "consumo: " + consumoEA +",energia: "+energia+", hora: " + hora ;
    }
}
