package lectura;

public class Lectura {
    private String numContador;
    private String fechaContador;
    private Double hora;
    private Double consumo;
    private String metodoObtencion;

    public Lectura(String numContador, String fechaContador, Double hora, Double consumo, String metodoObtencion) {
        this.numContador = numContador;
        this.fechaContador = fechaContador;
        this.hora = hora;
        this.consumo = consumo;
        this.metodoObtencion = metodoObtencion;
    }

    public String getNumContador() {
        return numContador;
    }

    public void setNumContador(String numContador) {
        this.numContador = numContador;
    }

    public String getFechaContador() {
        return fechaContador;
    }

    public void setFechaContador(String fechaContador) {
        this.fechaContador = fechaContador;
    }

    public Double getHora() {
        return hora;
    }

    public void setHora(Double hora) {
        this.hora = hora;
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
        return numContador +" "+fechaContador + " " +hora+" "+ consumo +" "+ metodoObtencion ;
    }
}
