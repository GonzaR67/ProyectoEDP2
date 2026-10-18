package Presentacion;

import Datos.Cola;
import Logica.Dron;
import Logica.Estacion;
import Logica.GestorRed;
import Logica.Mision;
import Logica.RobotTerrestre;
import Logica.VehiculoAutonomo;
import Logica.VehiculoElectrico;
import Logica.GestorVehiculos;
import java.util.List;

public class Menu {
    private GestorRed gestorRed;
    private GestorVehiculos gestorVehiculos;
    private Cola<Mision> M;

    public Menu() {
        gestorVehiculos = new GestorVehiculos();
        M = new Cola<>();
        gestorRed = new GestorRed();
        cargarCasosPruebaGrafo();
    }

    public void registrarVehiculo() {
        boolean seguir = true;

        while (seguir) {
            Consola.emitirMensaje("Ingrese el codigo del vehiculo: ");
            String cod = Consola.leerString();

            if (gestorVehiculos.buscarVehiculo(cod) != null) {
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

                    v.validarDatosGenerales(cod, mod, bat, cap, km);

                    // Insertamos usando el Gestor del Árbol
                    gestorVehiculos.agregarVehiculo(v);
                    Consola.emitirMensajeln("Vehiculo cargado correctamente en el arbol.");

                } catch (Exception e) {
                    Consola.emitirMensajeln(e.getMessage());
                    Consola.emitirMensajeln("Registro cancelado. Intente nuevamente.");
                }
            }

            boolean preguntar = true;
            while (preguntar) {
                Consola.emitirMensajeln("\nDesea registrar otro vehiculo? [Si / No]");
                String resp = Consola.leerString().trim().toLowerCase();
                if (resp.equals("si")) {
                    seguir = true;
                    preguntar = false;
                } else if (resp.equals("no")) {
                    seguir = false;
                    preguntar = false;
                } else {
                    Consola.emitirMensajeln("Error: Por favor ingrese 'Si' o 'No'.");
                }
            }
        }
    }

    public void mostrarVehiculos() {
        if (gestorVehiculos.estaVacio()) {
            Consola.emitirMensajeln("No hay vehiculos registrados en el sistema.");
            return;
        }

        Consola.emitirMensajeln("\n--- SELECCIONE EL TIPO DE RECORRIDO ---");
        Consola.emitirMensajeln("(1) InOrden (Ordenados por codigo)");
        Consola.emitirMensajeln("(2) PreOrden");
        Consola.emitirMensajeln("(3) PostOrden");
        Consola.emitirMensaje(">> ");
        int op = Consola.leerEntero();

        List<VehiculoAutonomo> lista;
        switch (op) {
            case 1: lista = gestorVehiculos.listarInOrden(); break;
            case 2: lista = gestorVehiculos.listarPreOrden(); break;
            case 3: lista = gestorVehiculos.listarPostOrden(); break;
            default: 
                Consola.emitirMensajeln("Opcion invalida, mostrando InOrden por defecto.");
                lista = gestorVehiculos.listarInOrden();
        }

        for (VehiculoAutonomo v : lista) {
            Consola.emitirMensajeln(v.toString());
        }
    }

    public void eliminarVehiculo() {
        if (gestorVehiculos.estaVacio()) {
            Consola.emitirMensajeln("No hay vehiculos para eliminar.");
            return;
        }
        Consola.emitirMensaje("Ingrese el codigo del vehiculo a eliminar: ");
        String cod = Consola.leerString();
        
        if (gestorVehiculos.eliminarVehiculo(cod)) {
            Consola.emitirMensajeln("Vehiculo eliminado exitosamente del arbol.");
        } else {
            Consola.emitirMensajeln("No se encontro ningun vehiculo con ese codigo.");
        }
    }

    public void mostrarCantidadVehiculos() {
        int cant = gestorVehiculos.contarVehiculos();
        Consola.emitirMensajeln("Cantidad total de vehiculos registrados: " + cant);
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
            
            boolean preguntar = true;
            while (preguntar) {
                Consola.emitirMensajeln("\nDesea registrar otra mision? [Si / No]");
                String resp = Consola.leerString().trim().toLowerCase();
                if (resp.equals("si")) {
                    seguir = true;
                    preguntar = false;
                } else if (resp.equals("no")) {
                    seguir = false;
                    preguntar = false;
                } else {
                    Consola.emitirMensajeln("Error: Por favor ingrese 'Si' o 'No'.");
                }
            }
        }
    }

    public void verDisponiblidad() {
        if (gestorVehiculos.estaVacio()) {
            Consola.emitirMensajeln("El sistema no tiene vehiculos registrados...");
            return;
        }
        boolean seguir = true;

        while (seguir) {
            Consola.emitirMensaje("Ingrese el codigo a verificar: ");
            String cod = Consola.leerString();
            VehiculoAutonomo v = gestorVehiculos.buscarVehiculo(cod);

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
            
            boolean preguntar = true;
            while (preguntar) {
                Consola.emitirMensajeln("\nDesea verificar la disponibilidad de otro vehiculo? [Si / No]");
                String resp = Consola.leerString().trim().toLowerCase();
                if (resp.equals("si")) {
                    seguir = true;
                    preguntar = false;
                } else if (resp.equals("no")) {
                    seguir = false;
                    preguntar = false;
                } else {
                    Consola.emitirMensajeln("Error: Por favor ingrese 'Si' o 'No'.");
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

        VehiculoAutonomo v = gestorVehiculos.buscarVehiculo(codVehiculo);
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
        if (gestorVehiculos.estaVacio()) {
            Consola.emitirMensajeln("No hay vehiculos registrados.");
            return;
        }

        Consola.emitirMensaje("Ingrese el codigo del vehiculo que desea recargar: ");
        String cod = Consola.leerString();
        VehiculoAutonomo v = gestorVehiculos.buscarVehiculo(cod);

        if (v != null) {
            if (v.getBateria() == 100) {
                Consola.emitirMensajeln("El vehiculo ya tiene la bateria al 100%.");
            } else {
                Consola.emitirMensajeln("Iniciando proceso de recarga...");
                v.recargar();
                Consola.emitirMensajeln("vehiculo recargado al 100%");
            }
        } else {
            Consola.emitirMensajeln("No se encontro un vehiculo con ese codigo.");
        }
    }
    
    private void cargarCasosPruebaGrafo() {
        gestorRed.agregarEstacion("E1", "Centro", "Norte");
        gestorRed.agregarEstacion("E2", "Plaza", "Centro");
        gestorRed.agregarEstacion("E3", "Parque", "Sur");
        gestorRed.agregarEstacion("E4", "Terminal", "Este");
        gestorRed.agregarEstacion("E5", "Universidad", "Oeste");

        gestorRed.agregarConexion("E1", "E2");
        gestorRed.agregarConexion("E1", "E4");
        gestorRed.agregarConexion("E2", "E3");
        gestorRed.agregarConexion("E3", "E5");
        gestorRed.agregarConexion("E4", "E5");
        gestorRed.agregarConexion("E2", "E5");
    }

    public void gestionarRedEstaciones() {
        int op;
        do {
            Consola.emitirMensajeln("\n--- GESTION DE RED DE ESTACIONES (GRAFOS) ---");
            Consola.emitirMensajeln("1. Mostrar la red completa (Estaciones y Conexiones)");
            Consola.emitirMensajeln("2. Consultar conexion directa entre dos estaciones");
            Consola.emitirMensajeln("3. Consultar estaciones vecinas");
            Consola.emitirMensajeln("4. Realizar recorrido DFS (Profundidad)");
            Consola.emitirMensajeln("5. Volver al menu principal");
            Consola.emitirMensaje(">> ");
            op = Consola.leerEntero();

            switch (op) {
                case 1:
                    Consola.emitirMensajeln("\n- Estaciones Registradas:");
                    for (Estacion e : gestorRed.obtenerTodasLasEstaciones()) {
                        Consola.emitirMensajeln("  " + e.toString());
                    }
                    Consola.emitirMensajeln("\n- Conexiones Existentes:");
                    for (String c : gestorRed.obtenerTodasLasConexiones()) {
                        Consola.emitirMensajeln("  " + c);
                    }
                    break;

                case 2:
                    Consola.emitirMensaje("Codigo de Estacion 1: ");
                    String c1 = Consola.leerString();
                    Consola.emitirMensaje("Codigo de Estacion 2: ");
                    String c2 = Consola.leerString();
                    
                    if (gestorRed.existeConexionDirecta(c1, c2)) {
                        Consola.emitirMensajeln("¡SI existe una conexion directa entre ellas!");
                    } else {
                        Consola.emitirMensajeln("NO existe una conexion directa.");
                    }
                    break;

                case 3:
                    Consola.emitirMensaje("Codigo de la Estacion central: ");
                    String codVecinas = Consola.leerString();
                    List<Estacion> vecinas = gestorRed.obtenerEstacionesVecinas(codVecinas);
                    
                    if (vecinas.isEmpty()) {
                        Consola.emitirMensajeln("La estacion no tiene vecinas o no existe.");
                    } else {
                        Consola.emitirMensajeln("Vecinas directas:");
                        for (Estacion v : vecinas) {
                            Consola.emitirMensajeln(" - " + v.getNombre());
                        }
                    }
                    break;

                case 4:
                    Consola.emitirMensaje("Codigo de Estacion inicial para el recorrido DFS: ");
                    String codInicio = Consola.leerString();
                    List<Estacion> recorrido = gestorRed.recorridoDFS(codInicio);
                    
                    if (recorrido.isEmpty()) {
                        Consola.emitirMensajeln("Estacion no encontrada.");
                    } else {
                        Consola.emitirMensajeln("Orden del Recorrido DFS:");
                        for (Estacion v : recorrido) {
                            Consola.emitirMensaje(v.getCodigo() + " -> ");
                        }
                        Consola.emitirMensajeln("FIN");
                    }
                    break;
                    
                case 5:
                    Consola.emitirMensajeln("Regresando...");
                    break;
                    
                default:
                    Consola.emitirMensajeln("Opcion invalida.");
            }
        } while (op != 5);
    }

    public void iniciar() {
        int op = 0;
        do {
            mostrarOpciones();
            op = Consola.leerEntero();
            switch (op) {
                case 1: registrarVehiculo(); break;
                case 2: registrarMision(); break;
                case 3: mostrarVehiculos(); break;
                case 4: verDisponiblidad(); break;
                case 5: asignarMision(); break;
                case 6: recargarVehiculo(); break;
                case 7: eliminarVehiculo(); break;
                case 8: mostrarCantidadVehiculos(); break;
                case 9: gestionarRedEstaciones(); break;
                case 10: Consola.emitirMensajeln("Saliendo..."); break;
                default: Consola.emitirMensajeln("Opcion invalida!");
            }
        } while (op != 10);
    }

    public void mostrarOpciones() {
        Consola.emitirMensajeln("\n\n----------GESTOR AUTOMOVE----------");
        Consola.emitirMensajeln("(1) Registrar Vehiculo");
        Consola.emitirMensajeln("(2) Registrar Mision");
        Consola.emitirMensajeln("(3) Mostrar Vehiculos y Recorridos");
        Consola.emitirMensajeln("(4) Determinar capacidad del vehiculo para una mision");
        Consola.emitirMensajeln("(5) Asignar mision a un vehiculo");
        Consola.emitirMensajeln("(6) Recargar un vehiculo");
        Consola.emitirMensajeln("(7) Eliminar un vehiculo");
        Consola.emitirMensajeln("(8) Mostrar cantidad de vehiculos");
        Consola.emitirMensajeln("(9) Gestionar Red de Estaciones (Grafos)");
        Consola.emitirMensajeln("(10) Salir");
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