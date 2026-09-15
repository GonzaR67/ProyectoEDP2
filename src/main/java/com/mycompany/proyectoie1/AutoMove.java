package com.mycompany.proyectoie1;
import java.util.Scanner;
public class AutoMove {
    Scanner sc=new Scanner(System.in);
    LSE<VehiculoAutonomo> E;
    
    public AutoMove(){
        E=new LSE<>();
    }
    
    public void mostrarOpciones(){
        System.out.println("\n\n----------GESTOR AUTOMOVE----------");
        System.out.println("(1)Registrar Vehiculo");
        System.out.println("(2)Registrar Mision");
        System.out.println("(3)Mostrar Vehiculos");
        System.out.println("(4)Asignar Mision");
        System.out.println("(5)Salir");
        System.out.println("\n>>");
    }
    
    public void mostrarTipos(){
        System.out.println("----------TIPOS DE VEHICULO----------");
        System.out.println("(1)Dron");
        System.out.println("(2)Robot Terrestre");
        System.out.println("(3)Vehiculo electrico");
        System.out.println("(4)Salir");
        System.out.println(">>>");
    }
    
    public void mostrarVehiculos(){
        Nodo<VehiculoAutonomo> p = E.inicio();
        if (p == null){
            System.out.println("No hay vehiculos aun");
            return;
        }
        while(p!=null){
            System.out.print("\nVehiculo autonomo de tipo:"+p.mostrar());
            p=p.getPs();
        }
    }
    
    public void registrarVehiculo(){
        int op;
        do{
            mostrarTipos();
            op=sc.nextInt();
            sc.nextLine();
            VehiculoAutonomo v = null;
            switch(op){
                case(1):
                    Dron d = new Dron();
                    d.leerDatos();
                    v=d;
                    break;
                case(2):
                    RobotTerrestre r = new RobotTerrestre();
                    r.leerDatos();
                    v=r;
                    break;
                case(3):
                    VehiculoElectrico e = new VehiculoElectrico();
                    e.leerDatos();
                    v=e;
                    break;
                case(4):
                    System.out.println("Volviendo al menu...");
                    break;
                default:
                    System.out.println("Error: opcion invalida!");
                    break;
            }
            if (v!=null){
                E.insertarPri(v);
                System.out.println("Vehiculo cargado correctamente");
            }
        }while(op!=4);
    }
    
    public void menu(){
        int op;
        do{
            mostrarOpciones();
            op=sc.nextInt();
            sc.nextLine();
            switch(op){
                case(1):
                    registrarVehiculo();
                    break;
                case(2):
                case(3):
                    mostrarVehiculos();
                    break;
                case(4):
                case(5):
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida!");
            }
        }while(op!=5);
    }
    public static void main(String[] args) {
        AutoMove app = new AutoMove();
        app.menu();
    }
}
