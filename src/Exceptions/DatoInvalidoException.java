/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author emami
 */
public class DatoInvalidoException extends Exception {
    public DatoInvalidoException() {
        super("Error: El dato ingresado es invalido o no se encontro el registro.");
    }

    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
