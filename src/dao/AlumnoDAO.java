package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Alumno;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lvazquezdorna
 */
public class AlumnoDAO {

    //ATRIBUTO DE CONEXION CONTRA LA BASE DE DATOS
    private AccesoDB acceso;

    //CONTRUCTORES
    public AlumnoDAO() throws SQLException, ClassNotFoundException {
        acceso = new AccesoDB();
    }

    //METODOS DE ACCESO A LA DB
    /**
     * Metodo que inserta un nuevo alumno en la db
     *
     * @param alumno
     * @throws SQLException si falla la sql
     * @throws ClassNotFoundException si falla la clase
     */
    public void insertarAlumno(Alumno alumno) throws SQLException, ClassNotFoundException {
        //montar consulta que vaya contra la base de datos
        String sql;
        Connection con;

        if (alumno == null) {
            return;
        }
        sql = "INSERT INTO alumnos (Nombre,Telefono,idCurso";
        if (alumno.getDireccion() != null) {
            sql += ",Direccion";
        }
        if (alumno.getDni() != null) {
            sql += " ,Dni";
        }
        sql += ") VALUES ('" + alumno.getNombre() + "', '" + alumno.getTelefono() + "', '" + alumno.getIdCurso() + "'";
        if (alumno.getDireccion() != null) {
            sql += ", '" + alumno.getDireccion() + "'";
        }
        if (alumno.getDni() != null) {
            sql += ", '" + alumno.getDni() + "'";
        }

        sql += ")";
        if (acceso == null) {
            return;
        }
        con = acceso.getConexion();

        Statement stm = con.createStatement();
        stm.execute(sql);//devuelve un entero q es el numero de filas insertadas, deberia devolver 1 por la persona insertada
        stm.close();
        con.close();
    }

    /**
     * Metodo que elimina un alumno por el numero de socio que es devuelto al
     * pasarle el objeto alumno este metodo se utiliza para eliminar de manera
     * grafica un alumno de la tabla y que se escoge una fila entera
     *
     * @param alumno
     * @return true si se encontro un alumno que eliminar y false en caso
     * contrario
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    //este metodo se utiliza para eliminar de manera grafica un alumno de la tabla y que se escoge una fila entera
    public boolean eliminarAlumno(Alumno alumno) throws SQLException, ClassNotFoundException {
        Connection con;
        Statement stm;
        if (alumno == null) {
            return false;
        }

        String sql = "DELETE FROM alumnos  WHERE nAlumno=" + alumno.getnAlumno();
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
     * Metodo que elimina un alumno dado el nAlumno
     *
     * @param nAlumno
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean eliminarAlumno(int nAlumno) throws SQLException, ClassNotFoundException {
        Connection con;
        Statement stm;

        String sql = "DELETE FROM alumnos WHERE nAlumno=" + nAlumno;
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
     * @param a
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    //Modificar 
    //otras forma de insertar sin ""''' mas sencilla donde no se conoce los campos y se ponen con ?? 
    //luego se mira con el pstm por orden los parametros mandados
    public void modificarAlumno(Alumno a) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = null;
        Connection con;
        String sql;
        //campos
        sql = "UPDATE alumnos SET nombre=?, telefono=?, dni=?,direccion=?, idCurso=? "
                + " WHERE codigocliente=" + a.getnAlumno();

        //conexion 
        if (acceso == null) {
            return;
        } else {
            con = acceso.getConexion();
        }
        pstm.setString(1, a.getNombre());
        pstm.setString(2, a.getTelefono());
        pstm.setString(3, a.getDni());
        pstm.setString(4, a.getDireccion());
        pstm.setInt(5, a.getIdCurso());
        pstm.executeUpdate();
        pstm.close();
        con.close();
    }

    /**
     *
     * @param nAl
     * @return
     */
    public Alumno getNumeroAlumno(int nAl) throws ClassNotFoundException, Exception {
        List<Alumno> listado;
        String sql;
        //campos
        sql = "SELECT * " + "FROM alumnos WHERE nAlumno=" + nAl;
        listado = leerAlumnos(sql);
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
    public List<Alumno> getAlumnoNombre(String nombre) throws ClassNotFoundException, Exception {
        List<Alumno> listado;
        String sql;
        //campos

        sql = "SELECT nAlumno, nombre, direccion, telefono, dni, idCurso " + "FROM alumnos WHERE nombre LIKE '%" + nombre + "%'";
        return listado = leerAlumnos(sql);
    }

    /**
     * metodo getAlumnos selecciona todas las columnas de la tabla y las
     * devuelve
     *
     * @return el listado de los alumnos de la tabla
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public List<Alumno> getAlumnos() throws ClassNotFoundException, Exception {
        List<Alumno> listado;
        String sql;
        //campos
        sql = "SELECT * FROM alumnos";
        return listado = leerAlumnos(sql);
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
    private List<Alumno> leerAlumnos(String sql) throws SQLException, ClassNotFoundException, Exception {
        List<Alumno> listado = new ArrayList<>();
        Connection con;
        Statement stm;
        ResultSet rs;
        //campos
        con = acceso.getConexion();
        stm = con.createStatement();
        rs = stm.executeQuery(sql);
        while (rs.next()) {
            Alumno a = new Alumno();

            a.setnAlumno(rs.getInt("nAlumno"));
            a.setNombre(rs.getString(("nombre")));
            a.setDni(rs.getString("dni"));
            a.setTelefono(rs.getString("telefono"));
            a.setDireccion(rs.getString("direccion"));
            a.setIdCurso(rs.getInt("idCurso"));

            listado.add(a);
        }
        rs.close();
        stm.close();
        con.close();
        return listado;
    }

    public List<Alumno> getNCurso(int idCurso) throws SQLException, ClassNotFoundException, Exception {
        List<Alumno> listado = new ArrayList<>();
        Connection con;
        Statement stm;
        ResultSet rs;
        //campos
        con = acceso.getConexion();
        stm = con.createStatement();
        rs = stm.executeQuery("SELECT * FROM alumnos where idCurso=" + idCurso);
        while (rs.next()) {
            Alumno a = new Alumno();

            a.setnAlumno(rs.getInt("nAlumno"));
            a.setNombre(rs.getString(("nombre")));
            a.setDni(rs.getString("dni"));
            a.setTelefono(rs.getString("telefono"));
            a.setDireccion(rs.getString("direccion"));
            a.setIdCurso(rs.getInt("idCurso"));

            listado.add(a);
        }
        rs.close();
        stm.close();
        con.close();
        return listado;
    }

}
