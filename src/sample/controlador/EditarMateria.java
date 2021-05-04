package sample.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import sample.controlador.arquitectura.Comunicador;
import sample.modelo.GestorDatos;
import sample.modelo.Grupo;
import sample.modelo.Materia;
import sample.modelo.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

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
        super.inicializarComponentes();
        materia = Materia.obtenerMateria(getMensaje());
        nombre.setText(materia.getNombre());
        grupo.setText(grupo.getText() + " " + materia.getGrupo());
        nrc.setText(grupo.getText() + "" + materia.getNrc());
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));

        ArrayList<Grupo> gruposObtenidos = null;
        try {
            gruposObtenidos = GestorDatos.obtenerGrupos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<Grupo> items = FXCollections.observableArrayList();
        gruposObtenidos.forEach(i-> items.add(i));
        grupos = new ComboBox<>(items);
    }

    public void regresar() {
        navegar(btnUsuario, "Información_Materia.fxml", getMensaje());
    }

    public void actualizarDatos() {
        materia.setGrupo(grupos.getValue());
        try {
            GestorDatos.actualizarMateria(materia);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
