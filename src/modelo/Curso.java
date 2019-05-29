/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import exception.NombreFormatException;

/**
 *
 * @author EliteDesk
 */
public class Curso {

    int idCurso;
    String nombre;

    public Curso() {
    }

    public Curso(String nombre) throws NombreFormatException {
        if (comprobarNombre(nombre)) {
            this.nombre = nombre.trim();
        } else {
            throw new NombreFormatException();
        }
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
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

    //METODOS DE CONTROL DE LOS ATRIBUTOS
    private boolean comprobarNombre(String nombre) {
        nombre = nombre.trim();
        if (nombre.isEmpty() || nombre.contains(" ")) {
            return false;
        }

        return true;
    }
}
