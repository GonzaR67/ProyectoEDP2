package Logica;

import Presentacion.NumeroNegativoExcepcion;

public class VehiculoElectrico extends VehiculoAutonomo {
    private int autonomiaKm;
    private int tiempoRecarga;

    public VehiculoElectrico() {
        super();
        this.autonomiaKm = 0;
        this.tiempoRecarga = 0;
    }

    public void validarDatosElectrico(int autonomiaKm, int tiempoRecarga) throws NumeroNegativoExcepcion {
        if (autonomiaKm <= 0) throw new NumeroNegativoExcepcion("Error: La autonomia debe ser mayor a cero.");
        if (tiempoRecarga <= 0) throw new NumeroNegativoExcepcion("Error: El tiempo de recarga debe ser mayor a cero.");
        
        setAutonomiaKm(autonomiaKm);
        setTiempoRecarga(tiempoRecarga);
    }

    @Override
    public boolean esApto(Mision m) {
        if (this.bateria < 20) return false;
        if (this.autonomiaKm < m.getDistancia()) return false;
        return true;
    }

    @Override
    public void recargar() {
        this.bateria = 100;
        this.estadoOperativo = "Disponible";
    }

    @Override
    public String toString() {
        return "Vehiculo Electrico" + super.toString() + "\nAutonomia en KM=" + autonomiaKm + "\nTiempo de Recarga=" + tiempoRecarga;
    }

    public int getAutonomiaKm() { 
        return autonomiaKm; }
    
    private void setAutonomiaKm(int autonomiaKm) { 
        this.autonomiaKm = autonomiaKm; }
    
    public int getTiempoRecarga() { 
        return tiempoRecarga; }
    
    private void setTiempoRecarga(int tiempoRecarga) { 
        this.tiempoRecarga = tiempoRecarga; }
}