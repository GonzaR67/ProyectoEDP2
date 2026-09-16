package Datos;

public class LSE<T> {
    private Nodo<T> list;
    
    public LSE(){
        list=null;
    }
    
    public Nodo<T> inicio(){
        return list;
    }
    
    public void insertarPri(T r){
        Nodo<T> x = new Nodo<T>(r,list);
        if(list!=null){
            x.setPs(list);
        }
        list=x;
    }
    
    public boolean estaVacia(){
        return list == null;
    }
}
