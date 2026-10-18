package Logica;

import Datos.ArbolABB;
import Presentacion.NivelBateriInvalidoExcepcion;
import Presentacion.NumeroNegativoExcepcion;
import Presentacion.StringLargo;
import Presentacion.StringVacio;
import java.util.List;

public class GestorVehiculos {

    private ArbolABB<VehiculoAutonomo> arbolVehiculos;

    public GestorVehiculos() {
        arbolVehiculos = new ArbolABB<>();
    }

    public boolean agregarVehiculo(VehiculoAutonomo v) {
        // Validamos que no exista otro con el mismo código
        if (buscarVehiculo(v.getCodigo()) != null) {
            return false; 
        }
        arbolVehiculos.insertar(v);
        return true;
    }

    // -------------------------------------------------
    // BÚSQUEDA GENÉRICA (El truco del profesor)
    // -------------------------------------------------
    public VehiculoAutonomo buscarVehiculo(String codigo) {
        // Creamos un vehículo "dummy" solo para usarlo como clave de búsqueda
        VehiculoAutonomo claveBuscadora = new Dron();
        try {
            // Le pasamos el código que buscamos, y rellenamos el resto con datos válidos 
            // para que no salten las validaciones de tu clase hermética
            claveBuscadora.validarDatosGenerales(codigo, "Aux", 100, 10, 10);
        } catch (NivelBateriInvalidoExcepcion | NumeroNegativoExcepcion | StringLargo | StringVacio e) {
            // No hacemos nada con el catch porque sabemos que los datos "Aux", 100, 10, 10 son válidos
        }
        
        return arbolVehiculos.buscar(claveBuscadora);
    }

    public boolean eliminarVehiculo(String codigo) {
        VehiculoAutonomo v = buscarVehiculo(codigo);
        if (v != null) {
            arbolVehiculos.eliminar(v);
            return true;
        }
        return false;
    }

    public int contarVehiculos() {
        return arbolVehiculos.contarElementos();
    }

    public List<VehiculoAutonomo> listarInOrden() {
        return arbolVehiculos.inOrden();
    }

    public List<VehiculoAutonomo> listarPreOrden() {
        return arbolVehiculos.preOrden();
    }

    public List<VehiculoAutonomo> listarPostOrden() {
        return arbolVehiculos.postOrden();
    }

    public boolean estaVacio() {
        return arbolVehiculos.estaVacio();
    }
}