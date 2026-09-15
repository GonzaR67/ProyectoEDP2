package com.mycompany.proyectoie1;
import java.util.Scanner;

public class Dron extends VehiculoAutonomo {
    private int autonomia;
    private int alturaMax;
    Scanner sc=new Scanner(System.in);
    
    public Dron(){
        this.autonomia=0;
        this.alturaMax=0;
    }
    
    public int getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }

    public int getAlturaMax() {
        return alturaMax;
    }

    public void setAlturaMax(int alturaMax) {
        this.alturaMax = alturaMax;
    }
    
    public void leerAuton(){
        boolean b=false;
        int num=0;
        do{
            System.out.println("Ingrese la autonomia de vuelo (minutos):");
            try{
                num=super.validarPos();
                b=true;
            }catch(NumeroValidoExcepcion | NumeroNegativoExcepcion  e){
                System.out.println(e.getMessage());
            }
        }while(!b);
        setAutonomia(num);
    }
    
    public void leerAltura(){
        boolean b=false;
        int num=0;
        do{
            System.out.println("Ingrese la altura maxima:");
            try{
                num=super.validarPos();
                b=true;
            }catch (NumeroValidoExcepcion | NumeroNegativoExcepcion e){
                System.out.println(e.getMessage());
            }
        }while(!b);
        setAlturaMax(num);
    }
    
    @Override
    public void leerDatos(){
        super.leerDatos();
        leerAuton();
        leerAltura();
    }

    @Override
    public String toString() {
        return  "Dron"+
                super.toString()+ 
                "\nAutonomia en minutos="+autonomia+
                "\nAltura maxima="+alturaMax;
    }

    @Override
    public double recargar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
