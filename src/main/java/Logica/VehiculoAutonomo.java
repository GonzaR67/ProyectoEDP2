package Logica;

import java.util.Scanner;

public abstract class VehiculoAutonomo implements Recargable {
    protected String codigo;
    protected String modelo;
    protected float bateria;
    protected int capacidadCarga;
    protected float km;
    protected String estadoOperativo;

    public VehiculoAutonomo() {
        this.codigo = " ";
        this.modelo = " ";
        this.bateria = 0;
        this.capacidadCarga = 0;
        this.km = 0;
        this.estadoOperativo = " ";
    }
    public void leerDatos(){
        leerCodigo();
        leerModelo();
        leerBateria();
        leerCapacidadCarga();
        leerKM();
        leerEstadoOpe();
    }
    
    public void leerCodigo(){
        Scanner sc=new Scanner(System.in);
        String str = " ";
        int b=0;
        do{
            System.out.println("Ingrese el codigo del vehiculo: ");
            str=sc.nextLine();
            try{
                validarString(str);
                b=1;
            }catch(StringVacio | StringLargo e){
                System.out.println(e.getMessage());
            }
        }while(b!=1);
        setCodigo(str);
    }
    
    public void leerModelo(){ 
        Scanner sc = new Scanner(System.in);
        int b=0;
        String str= " ";
        do{
            System.out.println("Ingrese el modelo: ");
            str= sc.nextLine();
            try{
                b = validarString(str);
                
            }catch(StringVacio  e){
                System.out.println(e.getMessage());
            }catch(StringLargo e){
                System.out.println(e.getMessage());
            }
   
        }while(b!=1);
        setModelo(str);
    }


    public void leerBateria(){
        int num = 0;
        int b=0;
        do{
            System.out.println("Ingrese el nivel de bateria [0-100]: ");
            try{
                num = validarBateria();
                b=1;
            }catch(NumeroValidoExcepcion e){
                System.out.println(e.getMessage());
                
            }catch(NumeroNegativoExcepcion e){
                System.out.println(e.getMessage());
            }catch (NivelBateriInvalidoExcepcion ex){
                System.out.println(ex.getMessage());
            }
        } while(b!=1);
        setBateria(num); 
    }
    public void leerCapacidadCarga(){
        boolean b = false;
        int num =0;
        do{
            System.out.println("Ingrese la capacidad de carga: ");
            try{
                num = validarPos(); 
                b=true;
            }catch (NumeroValidoExcepcion e){
                System.out.println(e.getMessage());
            }catch (NumeroNegativoExcepcion e){
                System.out.println(e.getMessage());
            }
   
        } while (!b);
        setCapacidadCarga(num);
        
    }
    public void leerKM(){
        boolean b = false;
        float num=0;
        do{
            System.out.println("Ingrese los kilometros: ");
            try{
                num = validarPosFloat();
                b=true;
            }catch(NumeroFlotanteExcepcion e){
                System.out.println(e.getMessage());
                
            }catch (NumeroNegativoExcepcion e){
                System.out.println(e.getMessage());
            }
        }while(!b);
        setKm(num);
        
    }
    public void leerEstadoOpe(){
        String str = " ";
        int opc = 0;
        do{
            System.out.println("Ingrese el estado operativo [1-Disponible 2-En mision 3-Cargando]: ");
            try{
                opc = validarPos();
            switch(opc){
                case 0:
                    System.out.println("Saliendo...");
                    break;
                case 1:
                    str = "Disponible";
                    break;
                case 2:
                    str = "En mision";
                    break;
                case 3:
                    str = "Cargando";
                    break;
                default:
                    System.out.println("Error: Ingrese una opcion valida [0-4]");
                    break;
            }
                
            }catch (NumeroValidoExcepcion e){
                System.out.println(e.getMessage());
            }catch (NumeroNegativoExcepcion e){
                System.out.println(e.getMessage());
            }
            
        } while(opc <0 || opc >4);
        setEstadoOperativo(str);
   
        
    }
    protected int validarString(String str) throws StringVacio, StringLargo {
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
            throw new NumeroNegativoExcepcion ("Error: Debe ingresar un numero positivo...");
        }
        return num;

    }
    
    private int validarBateria() throws NumeroValidoExcepcion, NumeroNegativoExcepcion, NivelBateriInvalidoExcepcion {
        int num = validarPos();
        if (num > 100) {
            throw new NivelBateriInvalidoExcepcion("Error: El nivel de bateria debe estar entre [0-100]");
        }
        if (num == 0) {
            System.out.println("Nivel de bateria 0: El dispositivo se apagará en 30 segundos...");
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
            throw new NumeroNegativoExcepcion ("Error: Debe ingresar un numero positivo...");
        }
        return num;
    }

    @Override
    public String toString() {
        return "\nCodigo="+codigo+
               "\nModelo="+modelo+
               "\nBateria="+bateria+
               "\nCapacidad de carga="+capacidadCarga+ 
               "\nKM="+km+
               "\nEstado operativo="+estadoOperativo;
                
               
    }
    
    
    public abstract boolean esApto();
    
    
    public void cambiarEstado(String s){
        this.estadoOperativo = s;
    }
    
    
    
    public String getCodigo() {
        return codigo;
    }

    private void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    private void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public float getBateria() {
        return bateria;
    }

    private void setBateria(float bateria) {
        this.bateria = bateria;
    }

    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    private void setCapacidadCarga(int capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public float getKm() {
        return km;
    }

    private void setKm(float km) {
        this.km = km;
    }

    public String getEstadoOperativo() {
        return estadoOperativo;
    }

    private void setEstadoOperativo(String estadoOperativo) {
        this.estadoOperativo = estadoOperativo;
    }
}

