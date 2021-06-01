package sample.controlador;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.collections4.Get;
import sample.controlador.arquitectura.Comunicador;
import sample.modelo.Estudiante;
import sample.modelo.GestorDatos;
import sample.modelo.Grupo;
import sample.modelo.Materia;

import java.sql.SQLException;
import java.util.HashMap;

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
                Estudiante nuevoEstudiante = new Estudiante(
                        "",
                        nombre.getText().trim().toUpperCase(),
                        apellidoPaterno.getText().trim().toUpperCase(),
                        apellidoMaterno.getText().trim().toUpperCase(),
                        grupos.getValue()
                );
                GestorDatos.insertarEstudiante(nuevoEstudiante);
                HashMap<Estudiante,Integer> calificaciones = new HashMap<>();
                calificaciones.put(nuevoEstudiante, 0);
                for (Materia materia: GestorDatos.obtenerMaterias(grupos.getValue())){
                    GestorDatos.insertarCalificaciones(calificaciones, materia);
                }

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
