package sample.controlador;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.controlador.arquitectura.Comunicador;
import sample.modelo.Estudiante;
import sample.modelo.GestorDatos;
import sample.modelo.Grupo;

import java.sql.SQLException;

public class NuevoEstudiante extends Comunicador {

    @FXML
    public Button btnUsuario;

    @FXML
    private TextField nombre;

    @FXML
    private TextField apellidoPaterno;

    @FXML
    private TextField apellidoMaterno;

    @FXML
    public Label grupo;

    @FXML
    public ComboBox<Grupo> grupos;

    @FXML
    public Label mensajeDeError;

    @Override
    public void inicializarComponentes() {
        try {
            grupos.setItems(FXCollections.observableArrayList(GestorDatos.obtenerGrupos()));
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void crear(){
        if (!nombre.getText().isBlank() && !apellidoMaterno.getText().isBlank() && !apellidoPaterno.getText().isBlank() && grupos.getValue() != null)
            try {
                GestorDatos.insertarEstudiante(
                        new Estudiante(
                                "",
                                nombre.getText().trim().toUpperCase(),
                                apellidoPaterno.getText().trim().toUpperCase(),
                                apellidoMaterno.getText().trim().toUpperCase(),
                                grupos.getValue()
                        )
                );
                navegar(nombre, "Lista_Grupos.fxml", getMensaje());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        else mensajeDeError.setVisible(true);
    }

    public void regresar() {
        navegar(nombre, "Lista_Grupos.fxml", getMensaje());
    }

}
