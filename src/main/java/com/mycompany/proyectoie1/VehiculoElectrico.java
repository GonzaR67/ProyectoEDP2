package com.mycompany.proyectoie1;
import java.util.Scanner;
public class VehiculoElectrico extends VehiculoAutonomo {
    private int autonomiaKm;
    private int tiempoRecarga;
    Scanner sc=new Scanner(System.in);

    public VehiculoElectrico() {
        this.autonomiaKm = 0;
        this.tiempoRecarga = 0;
    }
    
    public void leerAutoKm(){
        boolean b = false;
        int num = 0;
        do{
            System.out.println("Ingrese la autonomia de funcionamiento (kilometros):");
            num=sc.nextInt();
            try{
                num=super.validarPos();
                b=true;
            }catch(NumeroValidoExcepcion | NumeroNegativoExcepcion  e){
                System.out.println(e.getMessage());
            }
        }while(!b);
        setAutonomiaKm(num);
    }
    
    public void leerTiempoRec(){
        boolean b = false;
        int num = 0;
        do{
            System.out.println("Ingrese el tiempo de recarga (minutos):");
            num=sc.nextInt();
            try{
                num=super.validarPos();
                b=true;
            }catch(NumeroValidoExcepcion | NumeroNegativoExcepcion e){
                System.out.println(e.getMessage());
            }
        }while(!b);
        setTiempoRecarga(num);
    }
    
    public int getAutonomiaKm() {
        return autonomiaKm;
    }

    public void setAutonomiaKm(int autonomiaKm) {
        this.autonomiaKm = autonomiaKm;
    }

    public int getTiempoRecarga() {
        return tiempoRecarga;
    }

    public void setTiempoRecarga(int tiempoRecarga) {
        this.tiempoRecarga = tiempoRecarga;
    }
    
    @Override
    public void leerDatos(){
        super.leerDatos();
        leerAutoKm();
        leerTiempoRec();
    }
    
    @Override
    public String toString(){
        return  "Vehiculo Electrico"+ 
                super.toString()+
                "\nAutonomia en KM="+autonomiaKm+
                "\nTiempo de Recarga="+tiempoRecarga;
    }
    
    @Override
    public double recargar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
