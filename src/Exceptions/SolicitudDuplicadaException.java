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
public class SolicitudDuplicadaException extends Exception{
    public SolicitudDuplicadaException() {
        super("Error: Ya existe una solicitud registrada con ese codigo.");
    }

    public SolicitudDuplicadaException(String mensaje) {
        super(mensaje);
    }
}
