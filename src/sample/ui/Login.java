package sample.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.calificaciones.Administrativo;

public class Login implements Navegador {
    @FXML
    private TextField usuario;

    @FXML
    private PasswordField contraseña;

    @FXML
    private Label datosIncorrectos;


    public void iniciarSesion(ActionEvent actionEvent) {
        if(validarCampos(usuario.getText(), contraseña.getText()))
            navegar(usuario, "Calificaciones.fxml", "Calificaciones");
        else datosIncorrectos.setText("*Usuario y/o Contraseña incorrectos");
    }

    private boolean validarCampos(String usuario, String contraseña) {
        return (new Administrativo()).validarUsuarioContraseña(usuario,contraseña);
    }
}
