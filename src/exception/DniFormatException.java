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
public class DniFormatException extends Exception{
      private String mensaje ;

    public DniFormatException(String message) {
        super(message);
        this.mensaje=message;
    }

    public DniFormatException() {
        super("Excepcion en el formato del dni");
        this.mensaje="Excepcion en el formato del dni";
        
    }

    public String getMensajeError() {
        return this.mensaje;
    }
}
