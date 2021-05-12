package sample.controlador;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.controlador.arquitectura.Comunicador;
import sample.modelo.GestorDatos;
import sample.modelo.Grupo;
import sample.modelo.Profesor;
import sample.modelo.Usuario;

import java.sql.SQLException;

public class NuevoGrupo extends Comunicador {

    @FXML
    public TextField grado;

    @FXML
    public TextField letra;

    @FXML
    public ComboBox<Profesor> profesores;

    @FXML
    public Label mensajeDeError;

    @Override
    public void inicializarComponentes() {
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
        try {
            profesores.setItems(FXCollections.observableArrayList(GestorDatos.obtenerProfesores()));
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void crear() {
        if (!letra.getText().trim().equals("") && !grado.getText().trim().equals("") && profesores.getValue() != null) {
            try {
                GestorDatos.insertarGrupo(
                        new Grupo(
                                0,
                                Integer.parseInt(grado.getText().trim()),
                                letra.getText().trim().toUpperCase().charAt(0)
                        ),
                        profesores.getValue()
                );
                navegar(grado, "Lista_Grupos.fxml");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else mensajeDeError.setVisible(true);
    }

    public void regresar() {
        navegar(grado, "Lista_Grupos.fxml");
    }
}
