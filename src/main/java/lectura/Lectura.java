package lectura;

public class Lectura {
    private String numContador;
    private LocalDateTime fechaContador;
    private Double consumo;
    private String metodoObtencion;

    public Lectura(String numContador, LocalDateTime fechaContador, Double consumo, String metodoObtencion) {
        this.numContador = numContador;
        this.fechaContador = fechaContador;
        this.consumo = consumo;
        this.metodoObtencion = metodoObtencion;
    }

    public String getNumContador() {
        return numContador;
    }

    public void setNumContador(String numContador) {
        this.numContador = numContador;
    }

    public LocalDateTime getFechaContador() {
        return fechaContador;
    }

    public void setFechaContador(LocalDateTime fechaContador) {
        this.fechaContador = fechaContador;
    }

    public Double getConsumo() {
        return consumo;
    }

    public void setConsumo(Double consumo) {
        this.consumo = consumo;
    }

    public String getMetodoObtencion() {
        return metodoObtencion;
    }

    public void setMetodoObtencion(String metodoObtencion) {
        this.metodoObtencion = metodoObtencion;
    }

    @Override
    public String toString() {
        return numContador +" "+fechaContador + " " + consumo +" "+ metodoObtencion ;
    }
}
