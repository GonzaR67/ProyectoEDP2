package Logica;
public class Estacion {
    private String codIdentificador;
    private String nombre;
    private String zona;
    
    public Estacion(String codIdentificador, String nombre, String zona) {
        this.codIdentificador = codIdentificador;
        this.nombre = nombre;
        this.zona = zona;
    }

    public String getCodIdentificador() {
        return codIdentificador;
    }

    public void setCodIdentificador(String codIdentificador) {
        this.codIdentificador = codIdentificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
    
    
}
