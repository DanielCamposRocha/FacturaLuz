package consumos;

public record ConsumoEuros(double consumoEA, int hora, Double energia) {

    @Override
    public String toString() {
        return "consumo: " + consumoEA + ",energia: " + energia + ", hora: " + hora;
    }
}
