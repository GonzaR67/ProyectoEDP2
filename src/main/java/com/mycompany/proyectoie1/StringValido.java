/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.mycompany.proyectoie1;

/**
 *
 * @author ruizg
 */
public class StringValido extends Exception {

    /**
     * Creates a new instance of <code>StringValido</code> without detail
     * message.
     */
    public StringValido() {
    }

    /**
     * Constructs an instance of <code>StringValido</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public StringValido(String msg) {
        super(msg);
    }
}
