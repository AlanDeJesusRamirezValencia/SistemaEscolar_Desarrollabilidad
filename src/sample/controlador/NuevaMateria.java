package sample.controlador;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.controlador.arquitectura.Comunicador;
import sample.modelo.*;

import java.sql.SQLException;
import java.util.HashMap;

public class NuevaMateria extends Comunicador {

    @FXML
    public TextField nombre;

    @FXML
    public Label grupo;

    @FXML
    public Label mensajeDeError;

    @FXML
    private ComboBox<Grupo> grupos;

    @Override
    public void inicializarComponentes() {
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
        try {
            grupos.setItems(FXCollections.observableArrayList(GestorDatos.obtenerGrupos()));
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void regresar() {
        navegar(btnUsuario, "Lista_Grupos.fxml");
    }

    public void crear() {
        try {
            Materia nuevaMateria = new Materia(
                    0,
                    nombre.getText().trim().toUpperCase(),
                    grupos.getValue());
            if (!nombre.getText().isBlank() && grupos.getValue() != null) {
                GestorDatos.insertarMateria(nuevaMateria);
                HashMap<Estudiante,Integer> calificaciones = new HashMap<>();
                for (Estudiante estudiante: GestorDatos.obtenerEstudiantes(grupos.getValue())){
                    calificaciones.put(estudiante, 0);
                }
                GestorDatos.insertarCalificaciones(calificaciones, nuevaMateria);

                navegar(btnUsuario, "Lista_Grupos.fxml");
            }
            else mensajeDeError.setVisible(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
