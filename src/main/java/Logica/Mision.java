package Logica;

// ¡IMPORTANTE! Ya no importamos java.util.Scanner;

import Presentacion.NumeroNegativoExcepcion;
import Presentacion.StringLargo;
import Presentacion.StringVacio;


public class Mision {

    private String codigoMision;
    private String origen;
    private String destino;
    private float distancia;
    private float pesoCarga;
    private String prioridad;
    private String estado;

    public Mision() {
        this.codigoMision = "";
        this.origen = "";
        this.destino = "";
        this.distancia = 0;
        this.pesoCarga = 0;
        this.prioridad = "";
        this.estado = "disponible"; // Nace disponible por defecto, como pidió el profe
    }

    // -------------------------------------------------
    // 1. EL MÉTODO AGRUPADOR
    // Recibe todos los datos listos desde la Presentación
    // -------------------------------------------------
    public void validarDatos(String codigo, String origen, String destino, float distancia, float peso, String prioridad) 
            throws StringVacio, StringLargo, NumeroNegativoExcepcion {
        
        validarCodigo(codigo);
        validarOrigen(origen);
        validarDestino(destino);
        validarDistancia(distancia);
        validarPeso(peso);
        validarPrioridad(prioridad);
    }

    // -------------------------------------------------
    // 2. VALIDACIONES POR ATRIBUTO (Reemplazan a los leer...)
    // Controlan el dato y, si es correcto, llaman a su setter
    // -------------------------------------------------
    private void validarCodigo(String cod) throws StringVacio, StringLargo {
        validarString(cod);
        setCodigoMision(cod);
    }

    private void validarOrigen(String ori) throws StringVacio, StringLargo {
        validarString(ori);
        setOrigen(ori);
    }

    private void validarDestino(String dest) throws StringVacio, StringLargo {
        validarString(dest);
        setDestino(dest);
    }

    private void validarDistancia(float dist) throws NumeroNegativoExcepcion {
        if (dist <= 0) {
            throw new NumeroNegativoExcepcion("Error: La distancia debe ser un numero positivo...");
        }
        setDistancia(dist);
    }

    private void validarPeso(float peso) throws NumeroNegativoExcepcion {
        if (peso <= 0) {
            throw new NumeroNegativoExcepcion("Error: El peso de carga debe ser un numero positivo...");
        }
        setPesoCarga(peso);
    }

    private void validarPrioridad(String prio) {
        if (!prio.equalsIgnoreCase("Alta") && !prio.equalsIgnoreCase("Baja")) {
            throw new IllegalArgumentException("Error: La prioridad debe ser 'Alta' o 'Baja'.");
        }
        setPrioridad(prio);
    }

    // Tu validador de strings (arreglado según la corrección del profesor)
    private void validarString(String str) throws StringVacio, StringLargo {
        if (str == null || str.trim().isEmpty()) {
            throw new StringVacio("Error: El ingreso no puede estar vacio...");
        }
        if (str.length() > 10) {
            throw new StringLargo("Error: El ingreso supera el maximo permitido");
        }
    }

    // -------------------------------------------------
    // 3. SETTERS (Totalmente privados)
    // -------------------------------------------------
    private void setCodigoMision(String codigoMision) { this.codigoMision = codigoMision.trim(); }
    private void setOrigen(String origen) { this.origen = origen.trim(); }
    private void setDestino(String destino) { this.destino = destino.trim(); }
    private void setDistancia(float distancia) { this.distancia = distancia; }
    private void setPesoCarga(float pesoCarga) { this.pesoCarga = pesoCarga; }
    private void setPrioridad(String prioridad) { this.prioridad = prioridad.trim(); }

    // Este es el único método para modificar el estado una vez asignado
    public void actualizarEstado(String s) { this.estado = s; }

    // -------------------------------------------------
    // 4. GETTERS (Públicos)
    // -------------------------------------------------
    public String getCodigoMision() { return codigoMision; }
    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public float getDistancia() { return distancia; }
    public float getPesoCarga() { return pesoCarga; }
    public String getPrioridad() { return prioridad; }
    public String getEstado() { return estado; }
}