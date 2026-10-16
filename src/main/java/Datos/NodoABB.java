/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author Prof. Mauricio Hernandez
 */
public class NodoABB<T> {

    T dato;
    NodoABB<T> izquierdo;
    NodoABB<T> derecho;

    public NodoABB(T dato) {
        this.dato = dato;
        izquierdo = null;
        derecho = null;
    }

}
