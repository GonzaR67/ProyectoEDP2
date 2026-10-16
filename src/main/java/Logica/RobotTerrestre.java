package Logica;

import Presentacion.NumeroNegativoExcepcion;
import Presentacion.StringLargo;
import Presentacion.StringVacio;
import Presentacion.VelocidadMaximaExcepcion; // Asegúrate de tener esta excepcion creada

public class RobotTerrestre extends VehiculoAutonomo {
    private String tipoTerreno;
    private float velocidadMax;

    public RobotTerrestre() {
        super();
        this.tipoTerreno = "";
        this.velocidadMax = 0;
    }

    public void validarDatosRobot(String tipoTerreno, float velocidadMax) 
            throws StringVacio, StringLargo, NumeroNegativoExcepcion, VelocidadMaximaExcepcion {
        super.validarString(tipoTerreno);
        setTipoTerreno(tipoTerreno);

        if (velocidadMax <= 0) throw new NumeroNegativoExcepcion("Error: La velocidad debe ser mayor a cero.");
        if (velocidadMax > 50) throw new VelocidadMaximaExcepcion("Error: La velocidad no puede exceder los 50km por hora.");
        
        setVelocidadMax(velocidadMax);
    }

    @Override
    public boolean esApto(Mision m) {
        if (this.bateria < 40) return false;
        return true;
    }

    @Override
    public void recargar() {
        this.bateria = 100;
        this.estadoOperativo = "Disponible";
    }

    @Override
    public String toString() {
        return "Robot Terrestre" + super.toString() + "\nTipo de terreno=" + tipoTerreno + "\nVelocidad maxima=" + velocidadMax;
    }

    public String getTipoTerreno() { return tipoTerreno; }
    private void setTipoTerreno(String tipoTerreno) { this.tipoTerreno = tipoTerreno; }
    public float getVelocidadMax() { return velocidadMax; }
    private void setVelocidadMax(float velocidadMax) { this.velocidadMax = velocidadMax; }
}