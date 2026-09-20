package Logica;

import java.util.Scanner;

public class Dron extends VehiculoAutonomo {

    private int autonomia;
    private int alturaMax;

    public Dron() {
        this.autonomia = 0;
        this.alturaMax = 0;
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

    public void leerAuton() {
        Scanner sc = new Scanner(System.in);
        boolean b = false;
        int num = 0;
        do {
            System.out.println("Ingrese la autonomia de vuelo (minutos):");
            try {
                num = super.validarPos();
                b = true;
            } catch (NumeroValidoExcepcion | NumeroNegativoExcepcion e) {
                System.out.println(e.getMessage());
            }
        } while (!b);
        setAutonomia(num);
    }

    public void leerAltura() {
        Scanner sc = new Scanner(System.in);
        boolean b = false;
        int num = 0;
        do {
            System.out.println("Ingrese la altura maxima:");
            try {
                num = super.validarPos();
                b = true;
            } catch (NumeroValidoExcepcion | NumeroNegativoExcepcion e) {
                System.out.println(e.getMessage());
            }
        } while (!b);
        setAlturaMax(num);
    }

    @Override
    public void leerDatos() {
        super.leerDatos();
        leerAuton();
        leerAltura();
    }

    @Override
    public String toString() {
        return "Dron"
                + super.toString()
                + "\nAutonomia en minutos=" + autonomia
                + "\nAltura maxima=" + alturaMax;
    }
    
    
    public void esApto() {
        boolean b = true;
        
        if(this.bateria < 60){
            System.out.println("el vehiculo no tiene suficiente bateria para la mision");
            b = false;
        }
        
        
        if(b){
            System.out.println("El dron es apto para la mision");
        }else{
            System.out.println("El dron no está apto para la mision");
            
        }
        
        
        
    }
    
    @Override
    public void recargar() {
        this.bateria = 100;
        this.estadoOperativo = "Disponible";
        System.out.println("Bateria del Dron cargada al 100%.");
    }
    
}
