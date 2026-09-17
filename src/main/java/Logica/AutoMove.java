package Logica;

import Datos.Cola;
import Datos.Nodo;
import Logica.VehiculoAutonomo;
import Logica.Dron;
import Logica.VehiculoElectrico;
import Logica.RobotTerrestre;
import Datos.LSE;
import Logica.Mision;
import java.util.Scanner;
import Presentacion.Menu;


public class AutoMove {
    LSE<VehiculoAutonomo> E;
    Cola<Mision> M;

    public AutoMove() {
        E = new LSE<>();
        M = new Cola<>();
    }

    public void mostrarVehiculos() {
        Nodo<VehiculoAutonomo> p = E.inicio();
        if (p == null) {
            System.out.println("No hay vehiculos aun");
            return;
        }
        while (p != null) {
            System.out.print("\nVehiculo autonomo de tipo:" + p.mostrar());
            p = p.getPs();
        }
    }

    public void registrarVehiculo() {
        Scanner sc = new Scanner(System.in);
        Menu m = new Menu();
        int op;
        boolean seguir = true;
        while (seguir) {
            do {
                m.mostrarTipos();
                op = sc.nextInt();
                sc.nextLine();
                VehiculoAutonomo v = null;
                switch (op) {
                    case (1):
                        v = new Dron();
                        break;
                    case (2):
                        v = new RobotTerrestre();
                        break;
                    case (3):
                        v = new VehiculoElectrico();
                        break;
                    case (4):
                        System.out.println("Volviendo al menu...");
                        break;
                    default:
                        System.out.println("Error: opcion invalida!");
                        break;
                }

                if (v != null) {
                    v.leerDatos();
                    E.insertarPri(v);
                    System.out.println("Vehiculo cargado correctamente");

                    System.out.println("Desea generar mas vehiculos? [Si / No]");
                    String resp = sc.nextLine().toLowerCase();

                    if (resp.equalsIgnoreCase("no")) {
                        seguir = false;
                    }
                }

            } while (op != 4);
        }
    }

    public void registrarMision() {
        Scanner sc = new Scanner(System.in);
        int op;
        boolean seguir = true;
        
        Mision m = new Mision();

        while (seguir) {
                    m.leerDatos();
                    M.encolar(m);
                    System.out.println("Mision cargada correctamente");
                    System.out.println("Desea generar mas misiones? [Si / No]");
                    String resp = sc.nextLine().toLowerCase();

                    if (resp.equalsIgnoreCase("no")) {
                        seguir = false;
                    }
                }
        }


    public void menu() {
        Scanner sc = new Scanner(System.in);
        Menu m = new Menu();

        int op;
        do {
            m.mostrarOpciones();
            op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case (1):
                    registrarVehiculo();
                    break;
                case (2):
                    registrarMision();
                    break;
                case (3):
                    mostrarVehiculos();
                    break;
                case (4):
                case (5):
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida!");
            }
        } while (op != 5);
    }

    public static void main(String[] args) {
        AutoMove app = new AutoMove();
        app.menu();
    }
}