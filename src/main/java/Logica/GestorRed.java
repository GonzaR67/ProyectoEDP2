package Logica;

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;

public class GestorRed {

    private Graph<Estacion, DefaultEdge> grafo;

    public GestorRed() {
        grafo = new SimpleGraph<>(DefaultEdge.class);
    }

    public boolean agregarEstacion(String codigo, String nombre, String zona) {
        Estacion nueva = new Estacion();
        nueva.validarDatos(codigo, nombre, zona);

        if (grafo.containsVertex(nueva)) {
            return false;
        }
        grafo.addVertex(nueva);
        return true;
    }

    public Estacion buscarEstacionPorCodigo(String codigo) {
        Estacion clave = new Estacion();
        try {
            clave.validarDatos(codigo, "Aux", "Aux");
        } catch (IllegalArgumentException e) {
            return null;
        }
        
        for (Estacion e : grafo.vertexSet()) {
            if (e.equals(clave)) {
                return e;
            }
        }
        return null;
    }

    public boolean agregarConexion(String codEstacion1, String codEstacion2) {
        Estacion e1 = buscarEstacionPorCodigo(codEstacion1);
        Estacion e2 = buscarEstacionPorCodigo(codEstacion2);

        if (e1 != null && e2 != null && !e1.equals(e2)) {
            if (!grafo.containsEdge(e1, e2)) {
                grafo.addEdge(e1, e2);
                return true;
            }
        }
        return false;
    }

    public List<Estacion> obtenerTodasLasEstaciones() {
        return new ArrayList<>(grafo.vertexSet());
    }

    public List<String> obtenerTodasLasConexiones() {
        List<String> conexiones = new ArrayList<>();
        for (DefaultEdge arco : grafo.edgeSet()) {
            Estacion origen = grafo.getEdgeSource(arco);
            Estacion destino = grafo.getEdgeTarget(arco);
            conexiones.add(origen.getNombre() + " <---> " + destino.getNombre());
        }
        return conexiones;
    }

    public boolean existeConexionDirecta(String cod1, String cod2) {
        Estacion e1 = buscarEstacionPorCodigo(cod1);
        Estacion e2 = buscarEstacionPorCodigo(cod2);
        if (e1 != null && e2 != null) {
            return grafo.containsEdge(e1, e2);
        }
        return false;
    }

    public List<Estacion> obtenerEstacionesVecinas(String codigo) {
        Estacion e = buscarEstacionPorCodigo(codigo);
        if (e != null) {
            return Graphs.neighborListOf(grafo, e);
        }
        return new ArrayList<>();
    }

    public List<Estacion> recorridoDFS(String codigoInicio) {
        List<Estacion> recorrido = new ArrayList<>();
        Estacion inicio = buscarEstacionPorCodigo(codigoInicio);
        
        if (inicio != null) {
            DepthFirstIterator<Estacion, DefaultEdge> dfs = new DepthFirstIterator<>(grafo, inicio);
            while (dfs.hasNext()) {
                recorrido.add(dfs.next());
            }
        }
        return recorrido;
    }
}