/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Curso;

/**
 *
 * @author EliteDesk
 */
public class CursoDAO {

    //ATRIBUTO DE CONEXION CONTRA LA BASE DE DATOS

    private AccesoDB acceso;

    //CONTRUCTORES
    public CursoDAO() throws SQLException, ClassNotFoundException {
        acceso = new AccesoDB();
    }

    //METODOS DE ACCESO A LA DB
    /**
     * Metodo que inserta un nuevo curso en la tabla cursos de la db
     *
     * @param c objeto curso
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void insertarCurso(Curso c) throws SQLException, ClassNotFoundException {
        Connection con;
        PreparedStatement pstm = null;
        String sql;
        if (c == null) {
            return;
        }
        //campos
        sql = "INSERT INTO cursos(nombre) "
                + "VALUES (?)";

        //conexion 
        if (acceso == null) {
            return;
        } else {
            con = acceso.getConexion();
        }
        pstm = con.prepareStatement(sql);
        pstm.setString(1, c.getNombre());

        pstm.executeUpdate();
        pstm.close();
        con.close();
    }

    /**
     * Metodo que elimina un curso por el numero de socio que es devuelto al
     * pasarle el objeto curso este metodo se utiliza para eliminar de manera
     * grafica un alumno de la tabla y que se escoge una fila entera
     *
     * @param c
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean eliminarAlumno(Curso c) throws SQLException, ClassNotFoundException {
        Connection con;
        Statement stm;
        if (c == null) {
            return false;
        }

        String sql = "DELETE FROM cursos  WHERE nAlumno=" + c.getIdCurso();
        con = acceso.getConexion();
        stm = con.createStatement();
        int resultado = stm.executeUpdate(sql);
        stm.close();
        con.close();

        if (resultado > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que elimina un Curso dado el id
     *
     * @param idC
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean eliminarCurso(int idC) throws SQLException, ClassNotFoundException {
        Connection con;
        Statement stm;

        String sql = "DELETE FROM cursos WHERE idCurso=" + idC;
        con = acceso.getConexion();
        stm = con.createStatement();
        int resultado = stm.executeUpdate(sql);
        stm.close();
        con.close();

        if (resultado > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Modificar alumno de la db pasando un objeto alumno
     *
     * @param c
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    //Modificar 
    //otras forma de insertar sin ""''' mas sencilla donde no se conoce los campos y se ponen con ?? 
    //luego se mira con el pstm por orden los parametros mandados
    public void modificarCurso(Curso c) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = null;
        Connection con;
        String sql;
        //campos
        sql = "UPDATE cursos SET nombre=?"
                + " WHERE idCurso=" + c.getIdCurso();

        //conexion 
        if (acceso == null) {
            return;
        } else {
            con = acceso.getConexion();
        }
        pstm.setString(1, c.getNombre());
        pstm.executeUpdate();
        pstm.close();
        con.close();
    }

    /**
     *
     * @param parseInt
     * @return
     */
    public Curso getNumeroCurso(int idC) throws ClassNotFoundException, Exception {
        List<Curso> listado;
        String sql;
        //campos
        sql = "SELECT * FROM cursos WHERE idCurso=" + idC;
        listado = leerCursos(sql);
        if (listado != null && listado.size() == 1) {
            return listado.get(0);
        }
        return null;
    }

    /**
     * Metodo que recupera un alumno por el nombre
     *
     * @param text
     * @return
     */
    public List<Curso> getCursoNombre(String nombre) throws ClassNotFoundException, Exception {
        List<Curso> listado;
        String sql;
        //campos

        sql = "SELECT * FROM cursos WHERE nombre LIKE '%" + nombre + "%'";
        return listado = leerCursos(sql);
    }

    /**
     * metodo getAlumnos selecciona todas las columnas de la tabla y las
     * devuelve
     *
     * @return el listado de los alumnos de la tabla
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public List<Curso> getCursos() throws ClassNotFoundException, Exception {
        List<Curso> listado;
        String sql;
        //campos
        sql = "SELECT * FROM cursos";
        return listado = leerCursos(sql);
    }

    /**
     * recupera un registro de la tabla alumnos introduciendola en un arrayList
     *
     * @param sql
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    private List<Curso> leerCursos(String sql) throws SQLException, ClassNotFoundException, Exception {
        List<Curso> listado = new ArrayList<>();
        Connection con;
        Statement stm;
        ResultSet rs;
        //campos
        con = acceso.getConexion();
        stm = con.createStatement();
        rs = stm.executeQuery(sql);
        while (rs.next()) {
            Curso c = new Curso();

            c.setIdCurso(rs.getInt("idCurso"));
            c.setNombre(rs.getString(("nombre")));

            listado.add(c);
        }
        rs.close();
        stm.close();
        con.close();
        return listado;
    }

}
