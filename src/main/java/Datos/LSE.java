package Datos;

public class LSE<T> {
    private Nodo<T> list;
    
    public LSE(){
        list=null;
    }
    
    public Nodo<T> inicio(){
        return list;
    }
    
    public void insertarPri(T elem){
        Nodo<T> x = new Nodo<T>(elem);
        if(list!=null){
            x.enlazar(list);
        }
        list=x;
    }
    
    public boolean listaVacia(){
        return list == null;
    }
    
    /*public Nodo quitar(Nodo p, Nodo ant){
        Nodo x = p;

        if (p == list) {
            list = p.getPs();
        } else {
            ant.enlazar(p.getPs());

        }
        return x;
    } 
    
    public Nodo eliminar(int elem){
        Nodo x = null;
        Nodo p = list;
        Nodo ant = null;
        int b = 0;
        while(p != null && b == 0){
            if(p.getDato() == elem){
                x = quitar(p, ant);
                b = 1;
            }else{
                ant = p;
                p = p.getPs();
            }
        }
        
        if(b == 0){
            System.out.println("no existe el elemento");
        }else{
            System.out.println("elemento eliminado");
        }
        return x;
    }   */
}
