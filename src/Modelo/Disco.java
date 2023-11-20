package Modelo;

/**
 *
 * @author Fede
 */
public class Disco {
    //parametros
    private Float seekTimeMedio;
    private Integer velocidadRotacionRPM;
    private Float tiempoTransferenciaUnSector;
    private Integer tamañoBloque;
    private Integer totalPistas;
    private Integer posicionCabeza;
    private Character sentido;
    private Integer elementosEnPrimerLista_FSCAN;
    private Integer tamanioListaNSTEPSCAN;

    public Integer getTamanioListaNSTEPSCAN() {
        return tamanioListaNSTEPSCAN;
    }

    public void setTamanioListaNSTEPSCAN(Integer tamanioListaNSTEPSCAN) {
        this.tamanioListaNSTEPSCAN = tamanioListaNSTEPSCAN;
    }
    
    public Disco(Integer totalPistas, Integer posicionCabeza, Character sentido, Integer velocidadRotacionRPM, Float tiempoTransferenciaUnSector, Integer tamañoBloque, Float seekTimeMedio){
        this.totalPistas = totalPistas;
        this.posicionCabeza = posicionCabeza;
        this.sentido = sentido;
        this.velocidadRotacionRPM = velocidadRotacionRPM;
        this.tiempoTransferenciaUnSector = tiempoTransferenciaUnSector;
        this.tamañoBloque = tamañoBloque;
        this.seekTimeMedio = seekTimeMedio;
    }

    public Disco(Integer totalPistas, Integer posicionCabeza, Character sentido, Integer velocidadRotacionRPM, Float tiempoTransferenciaUnSector, Integer tamañoBloque, Float seekTimeMedio, Integer elementosEnPrimerLista_FSCAN, Integer tamanioListaNSTEPSCAN) {
        this.totalPistas = totalPistas;
        this.posicionCabeza = posicionCabeza;
        this.sentido = sentido;
        this.velocidadRotacionRPM = velocidadRotacionRPM;
        this.tiempoTransferenciaUnSector = tiempoTransferenciaUnSector;
        this.tamañoBloque = tamañoBloque;
        this.seekTimeMedio = seekTimeMedio;
        this.elementosEnPrimerLista_FSCAN = elementosEnPrimerLista_FSCAN;
        this.tamanioListaNSTEPSCAN = tamanioListaNSTEPSCAN;
    }

    public Float getSeekTimeMedio() {
        return seekTimeMedio;
    }

    public void setSeekTimeMedio(Float seekTimeMedio) {
        this.seekTimeMedio = seekTimeMedio;
    }

    public Integer getVelocidadRotacionRPM() {
        return velocidadRotacionRPM;
    }

    public void setVelocidadRotacionRPM(Integer velocidadRotacionRPM) {
        this.velocidadRotacionRPM = velocidadRotacionRPM;
    }

    public Float getTiempoTransferenciaUnSector() {
        return tiempoTransferenciaUnSector;
    }

    public void setTiempoTransferenciaUnSector(Float tiempoTransferenciaUnSector) {
        this.tiempoTransferenciaUnSector = tiempoTransferenciaUnSector;
    }

    public Integer getTamañoBloque() {
        return tamañoBloque;
    }

    public void setTamañoBloque(Integer tamañoBloque) {
        this.tamañoBloque = tamañoBloque;
    }

    public Integer getTotalPistas() {
        return totalPistas;
    }

    public void setTotalPistas(Integer totalPistas) {
        this.totalPistas = totalPistas;
    }

    public Integer getPosicionCabeza() {
        return posicionCabeza;
    }

    public void setPosicionCabeza(Integer posicionCabeza) {
        this.posicionCabeza = posicionCabeza;
    }

    public Character getSentido() {
        return sentido;
    }

    public void setSentido(Character sentido) {
        this.sentido = sentido;
    }
    
    public Integer getElementosEnPrimerLista_FSCAN() {
        return elementosEnPrimerLista_FSCAN;
    }

    public void setElementosEnPrimerLista_FSCAN(Integer elementosEnPrimerLista_FSCAN) {
        this.elementosEnPrimerLista_FSCAN = elementosEnPrimerLista_FSCAN;
    }
    
    
}
