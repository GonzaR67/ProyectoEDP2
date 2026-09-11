package com.mycompany.proyectoie1;
import java.util.Scanner;
public class AutoMove {
    Scanner sc=new Scanner(System.in);
    
    public void mostrarOpciones(){
        System.out.println("----------GESTOR AUTOMOVE----------");
        System.out.println("(1)Registrar Vehiculo");
        System.out.println("(2)Registrar Mision");
        System.out.println("(3)Mostrar Vehiculos");
        System.out.println("(4)Asignar Mision");
        System.out.println("(5)Salir");
        System.out.println("\n>>");
    }
    
    public void menu(){
        int op;
        do{
            mostrarOpciones();
            op=sc.nextInt();
            sc.nextLine();
            switch(op){
                case(1):
                case(2):
                case(3):
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
