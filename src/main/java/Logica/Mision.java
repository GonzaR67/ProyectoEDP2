package Logica;

import Logica.NumeroValidoExcepcion;
import Logica.NumeroFlotanteExcepcion;
import Logica.NumeroNegativoExcepcion;
import java.util.Scanner;

public class Mision {

    private String codigoMision;
    private String origen;
    private String destino;
    private float pesoCarga;
    private String prioridad;
    private String estado;

    public Mision() {
        this.codigoMision = " ";
        this.origen = " ";
        this.destino = " ";
        this.pesoCarga = 0;
        this.prioridad = " ";
        this.estado = "Disponible";
    }


    
    public void leerDatos(){
        leerOrigen();
        leerDestino();
        leerPeso();
        leerPrioridad();
        leerEstado();
    }
    
    public void leerOrigen(){ 
        Scanner sc = new Scanner(System.in);
        int b=0;
        String str= " ";
        do{
            System.out.println("Ingrese el origen: ");
            str= sc.nextLine();
            try{
                b = validarString(str);
                
            }catch(StringVacio  e){
                System.out.println(e.getMessage());
            }catch(StringLargo e){
                System.out.println(e.getMessage());
            }
   
        }while(b!=1);
        setOrigen(str);
    }
    
    public void leerDestino(){ 
        Scanner sc = new Scanner(System.in);
        int b=0;
        String str= " ";
        do{
            System.out.println("Ingrese el destino: ");
            str= sc.nextLine();
            try{
                b = validarString(str);
                
            }catch(StringVacio  e){
                System.out.println(e.getMessage());
            }catch(StringLargo e){
                System.out.println(e.getMessage());
            }
   
        }while(b!=1);
        setOrigen(str);
    }
    
    public void leerPeso(){
        boolean b = false;
        float num=0;
        do{
            System.out.println("Ingrese el peso: ");
            try{
                num = validarPosFloat();
                b=true;
            }catch(NumeroFlotanteExcepcion e){
                System.out.println(e.getMessage());
                
            }catch (NumeroNegativoExcepcion e){
                System.out.println(e.getMessage());
            }
        }while(!b);
        setPesoCarga(num);
    }
    
    public void leerPrioridad(){
        String str = " ";
        int opc = 0;
        do{
            System.out.println("Ingrese la prioridad [1-Alta 2-Baja]: ");
            try{
                opc = validarPos();
            switch(opc){
                case 1:
                    str = "Alta";
                    break;
                case 2:
                    str = "Baja";
                    break;
                default:
                    System.out.println("Error: Ingrese una opcion valida [1-2]");
                    break;
            }
                
            }catch (NumeroValidoExcepcion e){
                System.out.println(e.getMessage());
            }catch (NumeroNegativoExcepcion e){
                System.out.println(e.getMessage());
            }
            
            
        } while(opc < 1 || opc > 2);
        setPrioridad(str);
    }
    
    public void leerEstado(){
        String str = " ";
        int opc = 0;
        do{
            System.out.println("Ingrese el estado [1-Asignada 2-Disponible]: ");
            try{
                opc = validarPos();
            switch(opc){
                case 1:
                    str = "";
                    break;
                case 2:
                    str = "Baja";
                    break;
                default:
                    System.out.println("Error: Ingrese una opcion valida [1-2]");
                    break;
            }
                
            }catch (NumeroValidoExcepcion e){
                System.out.println(e.getMessage());
            }catch (NumeroNegativoExcepcion e){
                System.out.println(e.getMessage());
            }
            
        } while(opc < 1 || opc > 2);
        setPrioridad(str);
    }
    
    private int validarString(String str) throws StringVacio, StringLargo {
        int b = 0;
        if (!str.isEmpty()) {
            b = 1;
        } else if (str.isEmpty()) {
            throw new StringVacio("Error: El ingreso no puede estar vacio...");
        } else if (str.length() > 10) {
            throw new StringLargo("Error: El ingreso supera el maximo permitido");
        }
        return b;
    }
    
    
    private int validarNum() throws NumeroValidoExcepcion {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            throw new NumeroValidoExcepcion("Error: Debe ingresar un numero entero...");
        }
        return sc.nextInt();

    }

    private int validarPos() throws NumeroValidoExcepcion, NumeroNegativoExcepcion {
        int b = 0;
        int num = validarNum();
        if (num >= 0) {
            b = 1;
        } else {
            throw new NumeroNegativoExcepcion("Error: Debe ingresar un numero positivo...");
        }
        return num;

    }

    private float validarNumFloat() throws NumeroFlotanteExcepcion {
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

    public String getCodigoMision() {
        return codigoMision;
    }

    private void setCodigoMision(String codigoMision) {
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
