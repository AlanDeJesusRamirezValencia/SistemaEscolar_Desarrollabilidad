package sample.modelo;

import com.mysql.jdbc.Connection;
import javax.swing.*;
import java.sql.*;

/**
 * @author Abinadad
 * @version 11/03/2021
 */
class Conexion {
    
    private static final String DATA_BASE = "sistema_escolar";
    private static final String USER = "prueba";
    private static final String PASSWORD = "6%j60Ugv";
    private static final String SERVER = "74.208.242.71";
    private static final String URL = "jdbc:mysql://" + SERVER + ":3306/" + DATA_BASE;
    
    public static Connection getConexion(){
        Connection conexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
        } finally {
            return conexion;
        }
    }
}
