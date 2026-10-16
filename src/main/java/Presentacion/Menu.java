package Presentacion;

import Datos.Cola;
import Datos.ArbolABB;
import Datos.Nodo;
import Datos.NodoABB;
import Logica.Dron;
import Logica.GestorABB;
import Logica.Mision;
import Logica.RobotTerrestre;
import Logica.VehiculoAutonomo;
import Logica.VehiculoElectrico;
import java.util.List;

public class Menu {
    private GestorABB gestor;
    ArbolABB<VehiculoAutonomo> E;
    Cola<Mision> M;

    public Menu() {
        gestor = new GestorABB();
        E = new ArbolABB<>();
        M = new Cola<>();
    }

    public List<VehiculoAutonomo> mostarVehiculos(){
        return E.inOrden();
    }

    public void registrarVehiculo() {
        boolean seguir = true;
        while (seguir) {
            // 1. Pedimos el código PRIMERO usando leerString directamente
            Consola.emitirMensaje("Ingrese el codigo del vehiculo: ");
            String cod = Consola.leerString();

            // 2. Verificamos si existe
            if (buscarVehiculo(cod) != null) {
                Consola.emitirMensajeln("\nYa hay un vehiculo registrado con ese codigo.");
            } else {
                int op;
                do {
                    mostrarTipos();
                    op = Consola.leerEntero();
                    if (op < 1 || op > 3) {
                        Consola.emitirMensajeln("Error: opcion invalida!");
                    }
                } while (op > 3 || op < 1);

                // 4. Pedimos los atributos comunes usando leerString
                Consola.emitirMensaje("Ingrese el modelo: ");
                String mod = Consola.leerString();
                Consola.emitirMensaje("Ingrese la bateria [0-100]: ");
                double bat = Consola.leerPtoFlotante();
                Consola.emitirMensaje("Ingrese la capacidad de carga: ");
                int cap = Consola.leerEntero();
                Consola.emitirMensaje("Ingrese los kilometros: ");
                double km = Consola.leerPtoFlotante();

                VehiculoAutonomo v = null;

                try {
                    switch (op) {
                        case 1:
                            v = new Dron();
                            Consola.emitirMensaje("Ingrese autonomia de vuelo (minutos): ");
                            int autoDron = Consola.leerEntero();
                            Consola.emitirMensaje("Ingrese altura maxima: ");
                            int altura = Consola.leerEntero();
                            ((Dron) v).validarDatosDron(autoDron, altura);
                            break;

                        case 2:
                            v = new RobotTerrestre();
                            Consola.emitirMensaje("Ingrese el tipo de terreno: ");
                            String terreno = Consola.leerString();
                            Consola.emitirMensaje("Ingrese la velocidad maxima: ");
                            double vel = Consola.leerPtoFlotante();
                            ((RobotTerrestre) v).validarDatosRobot(terreno, (float) vel);
                            break;

                        case 3:
                            v = new VehiculoElectrico();
                            Consola.emitirMensaje("Ingrese la autonomia (km): ");
                            int autoElec = Consola.leerEntero();
                            Consola.emitirMensaje("Ingrese el tiempo de recarga (minutos): ");
                            int tiempo = Consola.leerEntero();
                            ((VehiculoElectrico) v).validarDatosElectrico(autoElec, tiempo);
                            break;
                    }

                    gestor.agregar(v,cod, mod, bat, cap, km);
                    
                    Consola.emitirMensajeln("Vehiculo cargado correctamente.");

                } catch (Exception e) {
                    Consola.emitirMensajeln(e.getMessage());
                    Consola.emitirMensajeln("Registro cancelado. Intente nuevamente.");
                }
            }

            boolean continuar = true;
            String resp = " ";
            while (continuar) {
                Consola.emitirMensajeln("\nDesea registrar otro vehiculo? [Si / No]");
                resp = Consola.leerString();
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
                        Consola.emitirMensajeln("Error: Ingrese [Si o No]");
                        break;
                }
            }
        }
    }

    public void registrarMision() {
        boolean seguir = true;
        while (seguir) {
            Consola.emitirMensaje("Ingrese el codigo de la mision: ");
            String cod = Consola.leerString();

            if (buscarMision(cod)) {
                Consola.emitirMensajeln("Ya hay una mision con ese codigo");
            } else {
                Consola.emitirMensaje("Ingrese el origen: ");
                String ori = Consola.leerString();
                Consola.emitirMensaje("Ingrese el destino: ");
                String dest = Consola.leerString();
                Consola.emitirMensaje("Ingrese la distancia: ");
                float dist = (float) Consola.leerPtoFlotante();
                Consola.emitirMensaje("Ingrese el peso: ");
                float peso = (float) Consola.leerPtoFlotante();
                Consola.emitirMensaje("Ingrese la prioridad [Alta/Baja]: ");
                String prio = Consola.leerString();

                Mision m = new Mision();
                try {
                    m.validarDatos(cod, ori, dest, dist, peso, prio);
                    M.encolar(m);
                    Consola.emitirMensajeln("Mision registrada correctamente");
                } catch (Exception e) {
                    Consola.emitirMensajeln(e.getMessage());
                }
            }
            boolean continuar = true;
            String resp = " ";
            while (continuar) {
                Consola.emitirMensajeln("\nDesea registrar otra mision? [Si / No]");
                resp = Consola.leerString();
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
                        Consola.emitirMensajeln("Error: Ingrese [Si o No]");
                        break;
                }
            }
        }
    }

    public void verDisponiblidad() {
        if (gestor.estaVacio()) {
            Consola.emitirMensajeln("La lista se encuentra vacia...");
            return;
        }
        boolean seguir = true;

        while (seguir) {
            Consola.emitirMensaje("Ingrese el codigo a verificar: ");
            String cod = Consola.leerString();
            VehiculoAutonomo v = buscarVehiculo(cod);

            if (v != null) {
                if (M.colaVacia()) {
                    Consola.emitirMensajeln("La cola de misiones se encuentra vacia");
                    return;
                }
                Mision m = M.frente();
                if (condicionesGenerales(v, m)) {
                    if (v.esApto(m)) {
                        Consola.emitirMensajeln("El vehiculo es completamente apto para la mision.");
                    } else {
                        Consola.emitirMensajeln("El vehiculo NO es apto (falla en los requisitos especificos de bateria/autonomia).");
                    }
                } else {
                    Consola.emitirMensajeln("Las condiciones generales no son aptas para la mision...");
                }
            } else {
                Consola.emitirMensajeln("No se encontro un vehiculo con ese codigo.");
            }
            boolean continuar = true;
            String resp = " ";
            while (continuar) {
                Consola.emitirMensajeln("\nDesea verificar la disponibilidad de otro vehiculo? [Si / No]");
                resp = Consola.leerString();
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
                        Consola.emitirMensajeln("Error: Ingrese [Si o No]");
                        break;
                }
            }
        }
    }

    public boolean condicionesGenerales(VehiculoAutonomo v, Mision m) {
        if (m == null) {
            Consola.emitirMensajeln("No hay misiones registradas para comparar la carga.");
            return false;
        }
        boolean apto = true;
        if (!v.getEstadoOperativo().equalsIgnoreCase("Disponible")) {
            apto = false;
        }
        if (m.getPesoCarga() > v.getCapacidadCarga()) {
            apto = false;
        }
        if (m.getDistancia() > v.getKm()) {
            apto = false;
        }
        return apto;
    }

    public void buscarVehiculo() {
        Consola.emitirMensaje("Ingrese el codigo del vehiculo a buscar: ");
        String cod = Consola.leerString();
        try{
            VehiculoAutonomo v = gestor.busqueda(cod);
            if (v==null){
                Consola.emitirMensaje("No existe un vehiculo con ese codigo");
            }else{
                Consola.emitirMensaje(v.getCodigo());
            }
        }catch(IllegalArgumentException e){
            Consola.emitirMensaje("Error: "+e.getMessage());
        }
    }

    public boolean buscarMision(String cod) {
        Cola<Mision> aux = new Cola<>();
        boolean b = false;
        while (!M.colaVacia()) {
            Mision m = M.desencolar();
            if (m.getCodigoMision().equals(cod)) {
                b = true;
            }
            aux.encolar(m);
        }
        retornar(M, aux);
        return b;
    }

    public void retornar(Cola<Mision> A, Cola<Mision> B) {
        while (!B.colaVacia()) {
            A.encolar(B.desencolar());
        }
    }

    public void asignarMision() {
        if (M.colaVacia()) {
            Consola.emitirMensajeln("No hay misiones pendientes.");
            return;
        }

        Mision m = M.desencolar();

        if (!m.getEstado().equalsIgnoreCase("Disponible")) {
            Consola.emitirMensajeln("La mision " + m.getCodigoMision() + " ya se encuentra en proceso.");
            M.encolar(m);
            return; 
        }

        Consola.emitirMensajeln("\n--- MISION A ASIGNAR ---");
        Consola.emitirMensajeln("Codigo: " + m.getCodigoMision());
        Consola.emitirMensajeln("Distancia: " + m.getDistancia() + " km | Peso: " + m.getPesoCarga() + " kg | Prioridad: " + m.getPrioridad());
        
        Consola.emitirMensaje("\nIngrese el codigo del vehiculo que realizara la mision: ");
        String codVehiculo = Consola.leerString();

        VehiculoAutonomo v = buscarVehiculo(codVehiculo);
        boolean exito = false;

        if (v != null) {
            if (condicionesGenerales(v, m)) {
                if (v.esApto(m)) {
                    v.cambiarEstado("en mision");
                    m.actualizarEstado("asignada");
                    Consola.emitirMensajeln("¡Mision asignada exitosamente!");
                    exito = true;
                } else {
                    Consola.emitirMensajeln("Asignacion fallida: El vehiculo no supera sus requisitos especificos.");
                }
            } else {
                Consola.emitirMensajeln("Asignacion fallida: Las condiciones generales no son aptas.");
            }
        } else {
            Consola.emitirMensajeln("No se encontro un vehiculo con ese codigo.");
        }
        
        if (!exito) {
            M.encolar(m);
            Consola.emitirMensajeln("La mision fue devuelta a la cola de pendientes.");
        }
    }

    public void recargarVehiculo() {
        if (E.listaVacia()) {
            Consola.emitirMensajeln("No hay vehiculos registrados.");
            return;
        }

        Consola.emitirMensaje("Ingrese el codigo del vehiculo que desea recargar: ");
        String cod = Consola.leerString();

        VehiculoAutonomo v = buscarVehiculo(cod);

        if (v != null) {
            if (v.getBateria() == 100) {
                Consola.emitirMensajeln("El vehiculo ya tiene la bateria al 100%.");
            } else {
                Consola.emitirMensajeln("Iniciando proceso de recarga...");
                v.recargar();
            }
        } else {
            Consola.emitirMensajeln("No se encontro un vehiculo con ese codigo.");
        }
    }

    public void iniciar() {
        int op = 0;
        do {
            mostrarOpciones();
            op = Consola.leerEntero();
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
                    Consola.emitirMensajeln("Saliendo...");
                    break;
                default:
                    Consola.emitirMensajeln("Opcion invalida!");
            }
        } while (op != 7);
    }

    public void mostrarOpciones() {
        Consola.emitirMensajeln("\n\n----------GESTOR AUTOMOVE----------");
        Consola.emitirMensajeln("(1) Registrar Vehiculo");
        Consola.emitirMensajeln("(2) Registrar Mision");
        Consola.emitirMensajeln("(3) Mostrar Vehiculos");
        Consola.emitirMensajeln("(4) Determinar capacidad del automovil para una mision");
        Consola.emitirMensajeln("(5) Asignar mision a un vehiculo");
        Consola.emitirMensajeln("(6) Recargar un vehiculo");
        Consola.emitirMensajeln("(7) Salir");
        Consola.emitirMensaje("\n>> ");
    }

    public void mostrarTipos() {
        Consola.emitirMensajeln("----------TIPOS DE VEHICULO----------");
        Consola.emitirMensajeln("(1) Dron");
        Consola.emitirMensajeln("(2) Robot Terrestre");
        Consola.emitirMensajeln("(3) Vehiculo electrico");
        Consola.emitirMensaje(">>> ");
    }
}