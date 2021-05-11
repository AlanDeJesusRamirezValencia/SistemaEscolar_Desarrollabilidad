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
        //TODO:Falta considerar no un profesor asignado
        if (letra.getText().trim().equals("") || grado.getText().trim().equals("") || profesores.getValue().equals(null)) {
            mensajeDeError.setVisible(true);
        } else {
            try {
                GestorDatos.insertarGrupo(
                        new Grupo(
                                0,
                                Integer.parseInt(grado.getText()),
                                letra.getText().toUpperCase().charAt(0),
                                profesores.getValue()
                        ),
                        true
                );
                navegar(grado, "Lista_Grupos.fxml");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void regresar() {
        navegar(grado, "Lista_Grupos.fxml");
    }
}
