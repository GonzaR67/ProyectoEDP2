package Logica;

import Presentacion.NumeroNegativoExcepcion;
import Presentacion.StringLargo;
import Presentacion.StringVacio;
import Presentacion.NivelBateriInvalidoExcepcion;

public abstract class VehiculoAutonomo implements Recargable, Comparable<VehiculoAutonomo> {
    protected String codigo;
    protected String modelo;
    protected double bateria;
    protected int capacidadCarga;
    protected double km;
    protected String estadoOperativo;

    public VehiculoAutonomo() {
        this.codigo = "";
        this.modelo = "";
        this.bateria = 0;
        this.capacidadCarga = 0;
        this.km = 0;
        this.estadoOperativo = "Disponible"; // Nace disponible por defecto
    }

    public void validarDatosGenerales(String codigo, String modelo, double bateria, int capacidadCarga, double km) 
            throws StringVacio, StringLargo, NumeroNegativoExcepcion, NivelBateriInvalidoExcepcion {
        
        validarCodigo(codigo);
        validarModelo(modelo);
        validarBateria(bateria);
        validarCapacidadCarga(capacidadCarga);
        validarKm(km);
    }

    // --- MÉTODOS DE VALIDACIÓN PRIVADOS ---

    private void validarCodigo(String cod) throws StringVacio, StringLargo {
        validarString(cod);
        setCodigo(cod);
    }

    private void validarModelo(String mod) throws StringVacio, StringLargo {
        validarString(mod);
        setModelo(mod);
    }

    private void validarBateria(double bat) throws NivelBateriInvalidoExcepcion {
        if (bat < 0 || bat > 100) {
            throw new NivelBateriInvalidoExcepcion("Error: El nivel de bateria debe estar entre [0-100].");
        }
        setBateria(bat);
    }

    private void validarCapacidadCarga(int cap) throws NumeroNegativoExcepcion {
        if (cap < 0) {
            throw new NumeroNegativoExcepcion("Error: La capacidad de carga no puede ser negativa.");
        }
        setCapacidadCarga(cap);
    }

    private void validarKm(double kilometros) throws NumeroNegativoExcepcion {
        if (kilometros < 0) {
            throw new NumeroNegativoExcepcion("Error: Los kilometros no pueden ser negativos.");
        }
        setKm(kilometros);
    }

    // Este método debe ser protected para que el RobotTerrestre pueda validar su terreno
    protected void validarString(String str) throws StringVacio, StringLargo {
        if (str == null || str.trim().isEmpty()) {
            throw new StringVacio("Error: El ingreso no puede estar vacio...");
        }
        if (str.length() > 15) { 
            throw new StringLargo("Error: El ingreso supera el maximo permitido");
        }
    }

    @Override
    public String toString() {
        return "\nCodigo=" + codigo +
               "\nModelo=" + modelo +
               "\nBateria=" + bateria +
               "\nCapacidad de carga=" + capacidadCarga + 
               "\nKM=" + km +
               "\nEstado operativo=" + estadoOperativo;
    }
    
    // Firma actualizada para poder comparar los atributos del vehículo con los de la misión
    public abstract boolean esApto(Mision m);
    
    public void cambiarEstado(String s){
        this.estadoOperativo = s;
    }
    
    // --- GETTERS (Públicos) y SETTERS (Privados) ---

    public String getCodigo() { return codigo; }
    private void setCodigo(String codigo) { this.codigo = codigo.trim(); }

    public String getModelo() { return modelo; }
    private void setModelo(String modelo) { this.modelo = modelo.trim(); }

    public double getBateria() { return bateria; }
    private void setBateria(double bateria) { this.bateria = bateria; }

    public int getCapacidadCarga() { return capacidadCarga; }
    private void setCapacidadCarga(int capacidadCarga) { this.capacidadCarga = capacidadCarga; }

    public double getKm() { return km; }
    private void setKm(double km) { this.km = km; }

    public String getEstadoOperativo() { return estadoOperativo; }
    private void setEstadoOperativo(String estadoOperativo) { this.estadoOperativo = estadoOperativo; }
    
    /**
     *
     * @param otro
     * @return
     */
    @Override
    public int compareTo(VehiculoAutonomo otro) {
        return this.codigo.compareTo(otro.getCodigo());
    }
    
}
