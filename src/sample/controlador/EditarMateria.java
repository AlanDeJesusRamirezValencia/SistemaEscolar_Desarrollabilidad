package sample.controlador;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.controlador.arquitectura.Comunicador;
import sample.modelo.GestorDatos;
import sample.modelo.Grupo;
import sample.modelo.Materia;
import sample.modelo.Usuario;

import java.sql.SQLException;

public class EditarMateria extends Comunicador {

    @FXML
    public TextField nombre;

    @FXML
    public Label grupo;

    @FXML
    public Label nrc;

    private Materia materia;

    @FXML
    private ComboBox<Grupo> grupos;

    @Override
    public void inicializarComponentes() {
        materia = Materia.obtenerMateria(getMensaje());
        nombre.setText(materia.getNombre());
        nrc.setText(nrc.getText() + " " + materia.getNrc());
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
        grupos.setValue(Grupo.obtenerGrupo(getMensaje()));
        try {
            grupos.setItems(FXCollections.observableArrayList(GestorDatos.obtenerGrupos()));
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void regresar() {
        navegar(btnUsuario, "Información_Materia.fxml", getMensaje());
    }

    public void actualizarDatos() {
        materia.setGrupo(grupos.getValue());
        materia.setNombre(nombre.getText());
        try {
            GestorDatos.actualizarMateria(materia);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
