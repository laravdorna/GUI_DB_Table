/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
        url = "jdbc:sqlite:registro.db";
      
        realizarConexion();
    }

    /**
     * Metodo  que inicia la conexion DB , salta execpcion si no es capaz de realizarla
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private void realizarConexion() throws SQLException, ClassNotFoundException {
        registarDriver();
        try{
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
    public void crearTablas() {       
        String sql1 = "CREATE TABLE IF NOT EXISTS cursos ("
                + " idCurso INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " nombre text"
                + ");";

   

        String sql2 = "CREATE TABLE IF NOT EXISTS alumnos ("
                + " nAlumno INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " nombre text,"
                + " direccion text,"
                + " dni text," 
                + " telefono text,"
                + " idCurso INTEGER NOT NULL,"
                + " FOREIGN KEY (idCurso)"
                + " REFERENCES cursos(idCurso)"
                + ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql1);
            stmt.execute(sql2);
      
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
}
