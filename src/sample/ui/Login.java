package sample.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.calificaciones.Administrativo;

import java.io.IOException;

public class Login {
    @FXML
    private TextField usuario;

    @FXML
    private PasswordField contraseña;

    @FXML
    private Label datosIncorrectos;


    public void iniciarSesion(ActionEvent actionEvent) {
        if(validarCampos(usuario.getText(), contraseña.getText())){
            try { irACalificaciones(); }
            catch (IOException e) {
                e.printStackTrace();
                e.getCause();
            }
        }
        else datosIncorrectos.setText("*Usuario y/o Contraseña incorrectos");
    }

    private void irACalificaciones() throws IOException {
        Parent nuevaEscena = FXMLLoader.load(getClass().getResource("../recursos/escenarios/Calificaciones.fxml"));
        Stage stageActual = (Stage) contraseña.getScene().getWindow();
        stageActual.setTitle("Calificaciones");
        stageActual.setScene(new Scene(nuevaEscena, 800, 600));
        stageActual.show();
    }

    private boolean validarCampos(String usuario, String contraseña) {
        return (new Administrativo()).validarUsuarioContraseña(usuario,contraseña);
    }
}
