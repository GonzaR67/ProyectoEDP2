package Datos;
public class Nodo<T> {
    private T dato;
    private Nodo<T> ps;

    public Nodo(T dato) {
        this.dato = dato;
        this.ps = null;
    }
    
    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getPs() {
        return ps;
    }

    private void setPs(Nodo<T> ps) {
        this.ps = ps;
    }
    
    public void enlazar(Nodo<T> ref){
        setPs(ref);
    }
    
    public String mostrar(){
        return getDato().toString();
    }
    
}
