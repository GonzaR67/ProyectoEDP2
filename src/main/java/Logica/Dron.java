package Logica;

import Presentacion.NumeroNegativoExcepcion;

public class Dron extends VehiculoAutonomo{
    private int autonomia;
    private int alturaMax;

    public Dron() {
        super();
        this.autonomia = 0;
        this.alturaMax = 0;
    }

    public void validarDatosDron(int autonomia, int alturaMax) throws NumeroNegativoExcepcion {
        if (autonomia <= 0) throw new NumeroNegativoExcepcion("Error: La autonomia debe ser mayor a cero.");
        if (alturaMax <= 0) throw new NumeroNegativoExcepcion("Error: La altura maxima debe ser mayor a cero.");
        
        setAutonomia(autonomia);
        setAlturaMax(alturaMax);
    }

    @Override
    public boolean esApto(Mision m) {
        if (this.bateria < 60) {
            return false;
        }
        return true;
    }

    @Override
    public void recargar() {
        this.bateria = 100;
        this.estadoOperativo = "Disponible";
    }

    @Override
    public String toString() {
        return "Dron" + super.toString() + "\nAutonomia en minutos=" + autonomia + "\nAltura maxima=" + alturaMax;
    }

    public int getAutonomia() { 
        return autonomia; }
    
    private void setAutonomia(int autonomia) { 
        this.autonomia = autonomia; }
    
    public int getAlturaMax() { 
        return alturaMax; }
    
    private void setAlturaMax(int alturaMax) { 
        this.alturaMax = alturaMax; }
}