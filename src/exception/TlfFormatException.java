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
public class TlfFormatException extends Exception {
  private String mensaje ;

    public TlfFormatException(String message) {
        super(message);
        this.mensaje=message;
    }

    public TlfFormatException() {
        super("Excepcion en el formato del telefono");
        this.mensaje="Excepcion en el formato del telefono";
        
    }

    public String getMensajeError() {
        return this.mensaje;
    }
    
}
