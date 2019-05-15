/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author octavio
 */
public class NombreFormatException extends Exception {

    private String mensaje ;

    public NombreFormatException(String message) {
        super(message);
        this.mensaje=message;
    }

    public NombreFormatException() {
        super("Excepcion en el formato del nombre");
        this.mensaje="Excepcion en el formato del nombre";
        
    }

    public String getMensajeError() {
        return this.mensaje;
    }

}
