package sample.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.controlador.arquitectura.Comunicador;
import sample.modelo.GestorDatos;
import sample.modelo.Profesor;
import sample.modelo.Usuario;

import java.sql.SQLException;

public class NuevoProfesor extends Comunicador {

    @FXML
    public TextField nombre;

    @FXML
    public TextField apellidoPaterno;

    @FXML
    public TextField apellidoMaterno;

    @FXML
    public Label mensajeDeError;

    @Override
    public void inicializarComponentes() {
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void crear() {

        if (!nombre.getText().isBlank() && !apellidoPaterno.getText().isBlank() && !apellidoMaterno.getText().isBlank()) {
            try {
                GestorDatos.insertarProfesor(
                        new Profesor(
                                0,
                                nombre.getText(),
                                apellidoPaterno.getText(),
                                apellidoMaterno.getText())
                );
                navegar(nombre, "Lista_Grupos.fxml");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else mensajeDeError.setVisible(true);
    }

    public void regresar() {
        navegar(nombre, "Lista_Grupos.fxml");
    }
}
