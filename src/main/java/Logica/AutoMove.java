package Logica;

import Datos.Cola;
import Datos.Nodo;
import Datos.LSE;
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
        VehiculoAutonomo v = null;
        while (seguir) {
            do {
                m.mostrarTipos();
                op = sc.nextInt();
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
                    default:
                        System.out.println("Error: opcion invalida!");
                        break;
                }
            } while (op > 3 || op < 1);

            v.leerDatos();
            E.insertarPri(v);
            System.out.println("Vehiculo cargado correctamente");
            sc.nextLine();

            boolean continuar = true;
            String resp = " ";
            while (continuar) {
                System.out.println("\nDesea registrar otro vehiculo? [Si / No]");
                resp = sc.nextLine().trim();
                switch (resp.toLowerCase()) {
                    case "si":
                        seguir = true;
                        continuar = false;
                        break;
                    case "no":
                        seguir = false;
                        continuar = false;
                        break;
                    default:
                        System.out.println("Error: Ingrese [Si o No]");
                        break;
                }

            }
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
            boolean continuar = true;
            String resp = " ";
            while (continuar) {
                System.out.println("\nDesea registrar otro vehiculo? [Si / No]");
                resp = sc.nextLine().trim();
                switch (resp.toLowerCase()) {
                    case "si":
                        seguir = true;
                        continuar = false;
                        break;
                    case "no":
                        seguir = false;
                        continuar = false;
                        break;
                    default:
                        System.out.println("Error: Ingrese [Si o No]");
                        break;
                }
            }
        }
    }

    public void verDisponiblidad() {
        if (E.listaVacia()) {
            System.out.println("La lista se encuentra vacia...");
            return;
        }
        Scanner sc = new Scanner(System.in);
        boolean seguir = true;

        while (seguir) {
            System.out.println("Ingrese el codigo a verificar: ");
            String cod = sc.nextLine();

            VehiculoAutonomo v = buscarVehiculo(cod);

            if (v != null) {
                Mision m = M.frente();
                if (condicionesGenerales(v, m)) {
                    v.esApto();
                } else {
                    System.out.println("Las condiciones generales no son aptas para la mision...");
                }
            } else {
                System.out.println("No se encontro un vehiculo con ese codigo.");
            }
            boolean continuar = true;
            String resp = " ";
            while (continuar) {
                System.out.println("\nDesea verificar la disponibilidad de otro vehiculo? [Si / No]");
                resp = sc.nextLine().trim();
                switch (resp.toLowerCase()) {
                    case "si":
                        seguir = true;
                        continuar = false;
                        break;
                    case "no":
                        seguir = false;
                        continuar = false;
                        break;
                    default:
                        System.out.println("Error: Ingrese [Si o No]");
                        break;
                }
            }
        }
    }

    public boolean condicionesGenerales(VehiculoAutonomo v, Mision m) {
        if (m == null) {
            System.out.println("No hay misiones registradas para comparar la carga.");
            return false;
        }
        boolean apto = true;
        if (!v.getEstadoOperativo().equalsIgnoreCase("Disponible")) {
            apto = false;
        }
        if (m.getPesoCarga() > v.getCapacidadCarga()) {
            apto = false;
        }
        if (m.getDistancia() > v.km ){
            apto = false;
        }
        return apto;
    }

    public VehiculoAutonomo buscarVehiculo(String cod) {
        Nodo<VehiculoAutonomo> p = E.inicio();
        VehiculoAutonomo x = null;
        while (p != null) {
            if (p.getDato().getCodigo().equals(cod)) {
                x = p.getDato();
            }
            p = p.getPs();
        }
        return x;
    }

    public void asignarMision() {
        if (M.colaVacia()) {
            System.out.println("No hay misiones pendientes.");
            return;
        }

        Mision m = M.desencolar();

        Scanner sc = new Scanner(System.in);
        System.out.println("Misión a asignar: " + m.getCodigoMision());
        System.out.println("Ingrese el código del vehículo que realizará la misión:");
        String codVehiculo = sc.nextLine();

        VehiculoAutonomo v = buscarVehiculo(codVehiculo);

        if (v != null) {
            if (condicionesGenerales(v, m)) {
                if (v.esApto()) {
                    v.cambiarEstado("en mision");
                    m.actualizarEstado("asignada");
                    System.out.println("Misión asignada exitosamente.");
                }
            } else {
                System.out.println("Las condiciones generales no son aptas para la mision...");
            }
        } else {
            System.out.println("No se encontro un vehiculo con ese codigo.");
        }
        M.encolar(m);
    }

    public void recargarVehiculo() {
        if (E.listaVacia()) {
            System.out.println("No hay vehiculos registrados.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el codigo del vehiculo que desea recargar:");
        String cod = sc.nextLine();

        VehiculoAutonomo v = buscarVehiculo(cod);

        if (v != null) {
            if (v.getBateria() == 100) {
                System.out.println("El vehiculo ya tiene la bateria al 100%.");
            } else {
                System.out.println("Iniciando proceso de recarga...");
                v.recargar();
            }
        } else {
            System.out.println("No se encontro un vehiculo con ese codigo.");
        }
    }

    private int validarNum() throws NumeroValidoExcepcion {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.nextLine();
            throw new NumeroValidoExcepcion("Error: Debe ingresar un numero entero...");
        }
        int num = sc.nextInt();
        return num;
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        Menu m = new Menu();
        int op = 0;
        do {
            m.mostrarOpciones();
            try {
                op = validarNum();

                switch (op) {
                    case 1:
                        registrarVehiculo();
                        break;
                    case 2:
                        registrarMision();
                        break;
                    case 3:
                        mostrarVehiculos();
                        break;
                    case 4:
                        verDisponiblidad();
                        break;
                    case 5:
                        asignarMision();
                        break;
                    case 6:
                        recargarVehiculo();
                        break;
                    case 7:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion invalida!");
                }
            } catch (NumeroValidoExcepcion e) {
                System.out.println(e.getMessage());
            }
        } while (op != 7);
    }

    public static void main(String[] args) {
        AutoMove app = new AutoMove();
        app.menu();
    }
}
