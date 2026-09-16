/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Logica;

/**
 *
 * @author ruizg
 */
public class NumeroNegativoExcepcion extends Exception {

    /**
     * Creates a new instance of <code>NumeroNegativoExcepcion</code> without
     * detail message.
     */
    public NumeroNegativoExcepcion() {
    }

    /**
     * Constructs an instance of <code>NumeroNegativoExcepcion</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NumeroNegativoExcepcion(String msg) {
        super(msg);
    }
}
