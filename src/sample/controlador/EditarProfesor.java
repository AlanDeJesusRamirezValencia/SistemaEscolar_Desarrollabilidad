package sample.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.controlador.arquitectura.Comunicador;

public class EditarProfesor extends Comunicador {

    @FXML
    public TextField nombre;

    @FXML
    public TextField apellidoPaterno;

    @FXML
    public TextField apellidoMaterno;

    @FXML
    public Button usuario;

    public void actualizarDatos() {
    }

    public void regresar() {
        navegar(nombre, "Información_Profesor.fxml", getMensaje());
    }
}
