/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.mycompany.proyectoie1;

/**
 *
 * @author ruizg
 */
public class NivelBateriInvalidoExcepcion extends Exception {

    /**
     * Creates a new instance of <code>NivelBateriInvalidoExcepcion</code>
     * without detail message.
     */
    public NivelBateriInvalidoExcepcion() {
    }

    /**
     * Constructs an instance of <code>NivelBateriInvalidoExcepcion</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NivelBateriInvalidoExcepcion(String msg) {
        super(msg);
    }
}
