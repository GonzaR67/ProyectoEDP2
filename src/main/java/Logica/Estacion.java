package Logica;

import java.util.Objects;

public class Estacion {
    private String codigo;
    private String nombre;
    private String zona;

    public Estacion() {
        this.codigo = " ";
        this.nombre = " ";
        this.zona = " ";
    }

    public void validarDatos(String codigo, String nombre, String zona) {
        validarCodigo(codigo);
        validarNombre(nombre);
        validarZona(zona);
    }

    private void validarCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo de estacion no puede estar vacio.");
        }
        setCodigo(codigo);
    }

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de estacion no puede estar vacio.");
        }
        setNombre(nombre);
    }

    private void validarZona(String zona) {
        if (zona == null || zona.trim().isEmpty()) {
            throw new IllegalArgumentException("La zona de la estacion no puede estar vacia.");
        }
        setZona(zona);
    }


    private void setCodigo(String codigo) { this.codigo = codigo.trim(); }
    private void setNombre(String nombre) { this.nombre = nombre.trim(); }
    private void setZona(String zona) { this.zona = zona.trim(); }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getZona() { return zona; }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nombre + " (" + zona + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estacion estacion = (Estacion) o;
        return Objects.equals(codigo, estacion.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}