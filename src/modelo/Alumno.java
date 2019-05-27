/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import exception.DireccionFormatException;
import exception.DniFormatException;
import exception.TlfFormatException;
import exception.NombreFormatException;

/**
 *
 * @author octavio
 */
public class Alumno {

    //ATRIBUTOS
    private int nAlumno;
    private String nombre;
    private String direccion;
    private String telefono;
    private String dni;
    private int idCurso;

    //CONSTRUCTORES
    public Alumno(String nombre, String telefono) throws NombreFormatException, TlfFormatException {

        if (comprobarNombre(nombre)) {
            this.nombre = nombre.trim();
        } else {
            throw new NombreFormatException();
        }
        String mensajeTlf = comprobarTlf(telefono);

        if (mensajeTlf == null) {
            this.telefono = telefono.trim();
        } else {
            throw new TlfFormatException(mensajeTlf);
        }

    }

    public Alumno(String nombre, String direccion, String telefono, String dni) throws NombreFormatException, TlfFormatException, DniFormatException, DireccionFormatException {

        if (comprobarNombre(nombre)) {
            this.nombre = nombre.trim();
        } else {
            throw new NombreFormatException();
        }

        if (comprobarDireccion(direccion)) {
            this.direccion = direccion;
        }

        String mensajeTlf = comprobarTlf(telefono);
        if (mensajeTlf == null) {
            this.telefono = telefono.trim();
        } else {
            throw new TlfFormatException(mensajeTlf);
        }

        String mensajeDni = comprobarDni(dni);
        if (mensajeDni == null) {
            this.dni = dni.toUpperCase().trim();
        }

    }

    public Alumno() {
    }

    //GET Y SET
    public int getnAlumno() {
        return nAlumno;
    }

    public void setnAlumno(int nAlumno) {
        this.nAlumno = nAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws NombreFormatException {
        if (comprobarNombre(nombre)) {
            this.nombre = nombre.trim();
        } else {
            throw new NombreFormatException();
        }
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) throws DireccionFormatException {
        if (comprobarDireccion(direccion)) {
            this.direccion = direccion;
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) throws TlfFormatException {
        String mensajeTlf = comprobarTlf(telefono);

        if (mensajeTlf == null) {
            this.telefono = telefono.trim();
        } else {
            throw new TlfFormatException(mensajeTlf);
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) throws DniFormatException {
        String mensajeDni = comprobarDni(dni);
        if (mensajeDni == null) {
            this.dni = dni.toUpperCase().trim();
        }
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
    

    //DATOS
    @Override
    public String toString() {
        return "Socio{" + "nSocio=" + nAlumno + ", nombre=" + nombre + ", telefono=" + telefono + "curso="+ idCurso+ '}';
    }

    public String datosSocio() {
        return "Socio{" + "nSocio=" + nAlumno + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", dni=" + dni  + "curso="+ idCurso+ '}';
    }

    //METODOS DE CONTROL DE LOS ATRIBUTOS
    private boolean comprobarNombre(String nombre) {
        nombre = nombre.trim();
        if (nombre.isEmpty() || nombre.indexOf(" ") == -1) {
            return false;
        }

        return true;
    }

    private String comprobarDni(String dni) {

        if (dni == null) {
            return "valor nulo";
        }

        boolean correcto = true;
        String resultado = null;
        String numeros, letrasDni = "TRWAGMYFPDXBNJZSQVHLCKE";
        char letra;
        long numerosDni;
        int resto;
        dni = dni.toUpperCase().trim();
        if (dni.length() != 9) {
            return "El dni debe tener 9 caracteres";
        } else {
            numeros = dni.substring(0, 8);
            for (int i = 0; i < numeros.length(); i++) {
                if (!Character.isDigit(numeros.charAt(i))) {
                    return "Los 8 primeros caracteres deben ser numeros";

                }
            }
            if (correcto) {
                letra = dni.charAt(8);
                if (!Character.isLetter(letra)) {

                    return "El ultimo caracter debe ser una letra";
                } else {//Comprobamos que la letra sea correcta
                    numerosDni = Long.parseLong(numeros);
                    resto = (int) (numerosDni % 23);
                    if (letra != letrasDni.charAt(resto)) {

                        return "La letra es incorrecta";
                    }
                }
            }

        }
        return resultado;
    }

    private String comprobarTlf(String telefono) {

        telefono = telefono.trim();
        if (telefono.length() != 9) {
            return "El telefono debe tener 9 numeros";
        } else {
            for (int i = 0; i < telefono.length(); i++) {
                if (!Character.isDigit(telefono.charAt(i))) {
                    return "Todos los caracteres deben ser numeros";
                }
            }
        }
        return null;
    }

    private boolean comprobarDireccion(String direccion) {
        if (direccion == null) {
            return false;
        }
        direccion = direccion.trim();
        if (direccion.isEmpty()) {
            return false;
        }
        return true;

    }
}
