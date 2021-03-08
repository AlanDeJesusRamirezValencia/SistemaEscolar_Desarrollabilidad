package sample.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.calificaciones.Gestor;
import sample.calificaciones.Usuario;

import java.sql.SQLException;

public class Login implements Navegador {
    @FXML
    private TextField usuario;

    @FXML
    private PasswordField contraseña;

    @FXML
    private Label datosIncorrectos;

    public void iniciarSesion(ActionEvent actionEvent) {
        if (validarContraseña(usuario.getText(),contraseña.getText()))
            navegar(usuario, "Calificaciones.fxml", "Calificaciones");
        else
            datosIncorrectos.setText("*Usuario y/o Contraseña incorrectos");
    }

    private boolean validarContraseña(String usuario, String contraseña){
        Usuario usuarioObtenido = null;
        try {
            usuarioObtenido = Gestor.obtenerUsuario(usuario);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contraseña.equals(usuarioObtenido.getContraseña());
    }
}
