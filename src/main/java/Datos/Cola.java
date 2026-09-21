
package Datos;
import Logica.Mision;
public class Cola<T> {
    private Nodo<T> fondo;
    private Nodo<T> frente;

    public Cola() {
        this.fondo = null;
        this.frente = null;
    }
    
    public <T> boolean colaVacia(){
        return frente == null;
    }
    
    public void encolar(T elem){
        Nodo<T> x = new Nodo<T>(elem);
        if (!colaVacia()) {
            fondo.enlazar(x);
        } else {
            frente = x;
        }
        fondo = x;
    }
    
    public T desencolar(){
        T x = null;
        if (!colaVacia()) {
            x = frente.getDato();
            frente = frente.getPs();
        } else {
            System.out.println("la cola esta vacia");
        }
        
        return x;
    }
    
    public boolean buscarMision(String cod) {
    Nodo<T> aux = frente;

    while (aux != null) {
        Mision m = (Mision) aux.getDato();

        if (m.getCodigoMision().equals(cod)) {
            return true;
        }

        aux = aux.getPs();
    }

    return false;
}
    
    public T frente(){
        return frente.getDato();
    }
    
    
}

