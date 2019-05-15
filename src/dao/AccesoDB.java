/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lvazquezdorna
 */
public class AccesoDB {
//atributos de conexion
    private Connection conn;
    private String url;
    //user y password no son necesarios, sirven para limitar el acceso a la base de datos
    private String user;
    private String password;

    //constructor donde se inicializan la url y el acceso a la base de datos y realiza la conexion cuando lo instancies
    public AccesoDB() throws SQLException, ClassNotFoundException {
        url = "jdbc:sqlite:chinook.db";
        user = "root";
        password = "root";
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
}
