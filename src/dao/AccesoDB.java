/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lvazquezdorna
 */
public class AccesoDB {
//atributos de conexion
    private Connection conn;
    private String url;
     static Statement stm = null;
    //user y password no son necesarios, sirven para limitar el acceso a la base de datos
    private String user;
    private String password;

    //constructor donde se inicializan la url y el acceso a la base de datos y realiza la conexion cuando lo instancies
    public AccesoDB() throws SQLException, ClassNotFoundException {
        url = "jdbc:sqlite:chinook.db";
      
        realizarConexion();
    }

    /**
     * Metodo  que inicia la conexion DB , salta execpcion si no es capaz de realizarla
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private void realizarConexion() throws SQLException, ClassNotFoundException {
        registarDriver();
        conn = DriverManager.getConnection(url, user, password);
    }

    private void registarDriver() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
    }

    /**
     * Metodo que comprueba si está realizada la conexion o si no, en caso de que
     * la conexión esté cerrada la inicia  y devuelve la conexion
     * @return la conexion 
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Connection getConexion() throws SQLException, ClassNotFoundException {
        if (conn == null || conn.isClosed()) {
            realizarConexion();
        }
        return conn;
    }
    
    
    /**
     * Método  establece un enlace con la base
     * de datos y ejecuta la secuencia sql que crea una
     * tabla nueva con sus respectivos parámetros. 
     * Si surge un error durante la
     * conexión saltará una excepción con el mensaje de error.
     *
     */
    public static void crearTablas() {
        String url = "jdbc:sqlite:chinook.db";
        
        String sql = "DROP TABLE IF EXISTS paises";
        
        String sql2 = "CREATE TABLE cursos (\n"
                + " id_curso integer PRIMARY KEY, \n"
                + " nombre text \n"
                + ");";

        String sql3 = "DROP TABLE IF EXISTS alumnos";

        String sql4 = "CREATE TABLE alumnos (\n"
                + " nAlumno integer PRIMARY KEY, \n"
                + " nombre text, \n"
                + " direccion text, \n"
                + " direccion telefono, \n"
                + " id_curso integer NOT NULL, \n"
                + " FOREIGN KEY (id_curso) \n"
                + " REFERENCES cursos(id_curso) \n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            stmt.execute(sql2);
            stmt.execute(sql3);
            stmt.execute(sql4);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
}
