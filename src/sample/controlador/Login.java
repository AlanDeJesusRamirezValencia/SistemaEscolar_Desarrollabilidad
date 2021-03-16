package sample.controlador;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.modelo.GestorDatos;
import sample.modelo.Usuario;
import sample.controlador.arquitectura.Comunicador;
import sample.controlador.arquitectura.UsuarioSingleton;

import java.sql.SQLException;

public class Login extends Comunicador {

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField contraseña;

    @FXML
    private Label datosIncorrectos;

    public void iniciarSesion() {
        if (validarContraseña(usuario.getText(),contraseña.getText())) {
            navegar(usuario, "Calificaciones.fxml");
        }
        else
            datosIncorrectos.setText("*Usuario y/o Contraseña incorrectos");
    }

    private boolean validarContraseña(String usuario, String contraseña){
        if (usuario.trim().equals("")) return false;
        try {
            Usuario usuarioObtenido = GestorDatos.obtenerUsuario(usuario);
            UsuarioSingleton.getInstance().setNombreUsuario(usuarioObtenido.getUsuario());
            return usuario.equals(usuarioObtenido.getUsuario())&&contraseña.equals(usuarioObtenido.getContraseña());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
