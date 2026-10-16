/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Datos.ArbolABB;
import Presentacion.NivelBateriInvalidoExcepcion;
import Presentacion.NumeroNegativoExcepcion;
import Presentacion.StringLargo;
import Presentacion.StringVacio;
import java.util.List;

/**
 *
 * @author Prof. Mauricio Hernandez
 */
public class GestorABB {

    private ArbolABB<VehiculoAutonomo> ABB;

    public GestorABB() {
        ABB = new ArbolABB<>();
    }

    // -------------------------------------------------
    // a) BÚSQUEDA: dado un número devuelve el contacto
    //    (con su estado) o null si no existe.
    //    Lanza IllegalArgumentException si el número es inválido.
    // -------------------------------------------------
    public VehiculoAutonomo busqueda(String numero) {
        // VehiculoAutonomo auxiliar que se usa solo como clave de búsqueda
        // (el árbol compara únicamente por número).
        VehiculoAutonomo clave = new VehiculoAutonomo();
        clave.validarDatos("b", numero, 0);
        return ABB.buscar(clave);
    }

    // -------------------------------------------------
    // b) AGREGAR: ingresa un c contacto favorito.
    //    Devuelve false si ya existe un contacto con ese número.
    //    Lanza IllegalArgumentException si algún dato es inválido.
    // -------------------------------------------------
    public boolean agregar(VehiculoAutonomo v,String cod, String mod, double bat, int cap, double km) throws StringVacio, StringLargo, NumeroNegativoExcepcion, NivelBateriInvalidoExcepcion {
        v.validarDatosGenerales(cod, mod, bat, cap, km);

        if (ABB.buscar(v) != null) {
            return false;
        }
        ABB.insertar(v);
        return true;
    }

    // -------------------------------------------------
    // c) MOSTRAR: devuelve todos los ABB en una lista,
    //    en orden ascendente por número (inOrden del árbol).
    //    La impresión la realiza el Menu.
    // -------------------------------------------------
    public List<VehiculoAutonomo> mostrar() {
        return ABB.inOrden();
    }

    public boolean estaVacio() {
        return ABB.estaVacio();
    }

}
