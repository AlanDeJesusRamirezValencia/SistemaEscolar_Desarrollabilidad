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

public class NuevaMateria extends Comunicador {

    @FXML
    public TextField nombre;

    @FXML
    public Label grupo;

    @FXML
    public Label nrc;

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
            GestorDatos.insertarMateria( new Materia(0, nombre.getText(), grupos.getValue()) );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        navegar(btnUsuario,"Lista_Grupos.fxml");
    }
}
