package Logica;

import Logica.NumeroValidoExcepcion;
import Logica.NumeroFlotanteExcepcion;
import Logica.NumeroNegativoExcepcion;
import java.util.Scanner;

public class Mision {

    private int codigoMision;
    private String origen;
    private String destino;
    private float pesoCarga;
    private String prioridad;
    private String estado;

    public Mision() {
        this.codigoMision = 0;
        this.origen = " ";
        this.destino = " ";
        this.pesoCarga = 0;
        this.prioridad = " ";
        this.estado = " ";
    }


    
    public void leerDatos(){
        leerCodigo();
        
    }
    
    
    protected int validarNum() throws NumeroValidoExcepcion {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            throw new NumeroValidoExcepcion("Error: Debe ingresar un numero entero...");
        }
        return sc.nextInt();

    }

    protected int validarPos() throws NumeroValidoExcepcion, NumeroNegativoExcepcion {
        int b = 0;
        int num = validarNum();
        if (num >= 0) {
            b = 1;
        } else {
            throw new NumeroNegativoExcepcion("Error: Debe ingresar un numero positivo...");
        }
        return num;

    }

    protected float validarNumFloat() throws NumeroFlotanteExcepcion {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextFloat()) {
            throw new NumeroFlotanteExcepcion("Error: Debe ingresar un numero valido...");
        }
        return sc.nextFloat();

    }

    private float validarPosFloat() throws NumeroFlotanteExcepcion, NumeroNegativoExcepcion {
        int b = 0;
        float num = validarNumFloat();
        if (num > 0) {
            b = 1;
        } else {
            throw new NumeroNegativoExcepcion("Error: Debe ingresar un numero positivo...");
        }
        return num;
    }

    public int getCodigoMision() {
        return codigoMision;
    }

    private void setCodigoMision(int codigoMision) {
        this.codigoMision = codigoMision;
    }

    public String getOrigen() {
        return origen;
    }

    private void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    private void setDestino(String destino) {
        this.destino = destino;
    }

    public float getPesoCarga() {
        return pesoCarga;
    }

    private void setPesoCarga(float pesoCarga) {
        this.pesoCarga = pesoCarga;
    }

    public String getPrioridad() {
        return prioridad;
    }

    private void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstado() {
        return estado;
    }

    private void setEstado(String estado) {
        this.estado = estado;
    }

}
