package sample.calificaciones;

import com.mysql.jdbc.Connection;
import javax.swing.*;
import java.sql.*;

class Conexion {
    
    private static final String DATA_BASE = "rhtyjofc_controlescolar";
    private static final String USER = "rhtyjofc";
    private static final String PASSWORD = "VQ9vGqT86n7n";
    private static final String SERVER = "50.31.177.71";
    private static final String URL = "jdbc:mysql://" + SERVER + ":3306/" + DATA_BASE;
    
    public static Connection getConexion(){
        Connection conexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conexion = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error" + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        } finally {
            return conexion;
        }
    }
}
