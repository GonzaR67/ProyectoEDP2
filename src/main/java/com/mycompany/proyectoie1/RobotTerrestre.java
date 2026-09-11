package com.mycompany.proyectoie1;

import java.util.Scanner;

public class RobotTerrestre extends VehiculoAutonomo{
    private String tipoTerreno;
    private float velocidadMax;

    @Override
    public double recargar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public RobotTerrestre() {
        super();
        this.tipoTerreno = " ";
        this.velocidadMax = 0;
    }
    
    
    @Override
    public void leerDatos(){
        super.leerDatos();
        leerTipoTerreno();
        leerVelocidad();
    }
    
    public void leerTipoTerreno(){
        Scanner sc = new Scanner(System.in);
        int b=0;
        String str= " ";
        do{
            System.out.println("Ingrese el tipo de terreno: ");
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
    
    
    public void leerVelocidad(){
        float num = 0;
        int b=0;
        do{
            System.out.println("Ingrese la velocidad maxima: ");
            try{
                num = validarNumFloat();
                b=1;
            }catch(NumeroFlotanteExcepcion e){
                System.out.println(e.getMessage());
                
            }catch(NumeroNegativoExcepcion e){
                System.out.println(e.getMessage());
            }catch (VelocidadMaximaExcepcion e){
                System.out.println(e.getMessage());
            }
        } while(b!=1);
        setVelocidadMax(num); 

    }
    
    
    private float validarVelocidad() throws VelocidadMaximaExcepcion{
        float num = validarNumFloat();
        
        if(num >50){
            throw new VelocidadMaximaExcepcion("la velocidad");
        }
    }

    public String getTipoTerreno() {
        return tipoTerreno;
    }

    private void setTipoTerreno(String tipoTerreno) {
        this.tipoTerreno = tipoTerreno;
    }

    public float getVelocidadMax() {
        return velocidadMax;
    }

    private void setVelocidadMax(float velocidadMax) {
        this.velocidadMax = velocidadMax;
    }
    
    
    
    
    
    
}
