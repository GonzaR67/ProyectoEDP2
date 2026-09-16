package Logica;

import Logica.NumeroFlotanteExcepcion;
import Logica.VelocidadMaximaExcepcion;
import Logica.StringLargo;
import Logica.StringVacio;
import java.util.Scanner;

public class RobotTerrestre extends VehiculoAutonomo{
    private String tipoTerreno;
    private float velocidadMax;

    public RobotTerrestre() {
        super();
        this.tipoTerreno = " ";
        this.velocidadMax = 0;
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
    
    public void leerTipoTerreno(){
        Scanner sc = new Scanner(System.in);
        int b=0;
        String str= " ";
        do{
            System.out.println("Ingrese el tipo de terreno: ");
            str= sc.nextLine();
            try{
                b = validarString(str);
                
            }catch(StringVacio | StringLargo  e){
                System.out.println(e.getMessage());
            }
        }while(b!=1);
        setTipoTerreno(str);
        
    }
    
    public void leerVelocidad(){
        float num = 0;
        int b=0;
        do{
            System.out.println("Ingrese la velocidad maxima: ");
            try{
                num = validarVelocidad();
                b=1;
            }catch(NumeroFlotanteExcepcion | VelocidadMaximaExcepcion e){
                System.out.println(e.getMessage()); 
            }
        } while(b!=1);
        setVelocidadMax(num); 

    }
    
    private float validarVelocidad() throws VelocidadMaximaExcepcion, NumeroFlotanteExcepcion{
        float num = validarNumFloat();
        
        if(num >50){
            throw new VelocidadMaximaExcepcion("la velocidad");
        }
        return 0;
    }

    @Override
    public void leerDatos(){
        super.leerDatos();
        leerTipoTerreno();
        leerVelocidad();
    }

    @Override
    public String toString(){
        return  "Robot Terrestre"+
                super.toString()+
                "\nTipo de terreno="+tipoTerreno+
                "\nVelocidad maxima="+velocidadMax;
    }    
    
    @Override
    public double recargar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
