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
    private PasswordField contrasena;

    @FXML
    private Label datosIncorrectos;

    public void iniciarSesion() {
        if (validarContrasena(usuario.getText(),contrasena.getText())) {
            navegar(usuario, "Lista_Grupos.fxml");
        }
        else
            datosIncorrectos.setText("*Usuario y/o Contraseña incorrectos");
    }

    private boolean validarContrasena(String usuario, String contrasena){
        if (usuario.trim().equals("")) return false;
        usuario = usuario.toLowerCase();
        try {
            Usuario usuarioObtenido = GestorDatos.obtenerUsuario(usuario);
            UsuarioSingleton.getInstance().setNombreUsuario(usuarioObtenido.getUsuario());
            return usuario.equals(usuarioObtenido.getUsuario())&&contrasena.equals(usuarioObtenido.getContraseña());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
