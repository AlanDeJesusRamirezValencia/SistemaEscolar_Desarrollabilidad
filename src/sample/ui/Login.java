package sample.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.calificaciones.Gestor;
import sample.calificaciones.Grupo;
import sample.calificaciones.Usuario;
import sample.ui.arquitectura.Comunicador;
import sample.ui.arquitectura.UsuarioSingleton;

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
        Usuario usuarioObtenido = new Usuario("","");
        try {
            usuarioObtenido = Gestor.obtenerUsuario(usuario);
            UsuarioSingleton.getInstance().setNombreUsuario(usuarioObtenido.getUsuario());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contraseña.equals(usuarioObtenido.getContraseña());
    }
}
