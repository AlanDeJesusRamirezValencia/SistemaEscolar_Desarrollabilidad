package sample.controlador;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.controlador.arquitectura.Comunicador;
import sample.modelo.GestorDatos;
import sample.modelo.Grupo;
import sample.modelo.Materia;
import sample.modelo.Usuario;

import java.sql.SQLException;
import java.util.Optional;

public class EditarMateria extends Comunicador {

    @FXML
    public TextField nombre;

    @FXML
    public Label grupo;

    @FXML
    public Label nrc;

    private Materia materiaActual;

    @FXML
    private ComboBox<Grupo> grupos;

    @FXML
    public Label mensajeDeError;

    @Override
    public void inicializarComponentes() {
        materiaActual = Materia.obtenerMateria(getMensaje());
        nombre.setText(materiaActual.getNombre());
        nrc.setText(nrc.getText() + " " + materiaActual.getNrc());
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
        grupos.setValue(Grupo.obtenerGrupo(getMensaje()));
        try {
            grupos.setItems(FXCollections.observableArrayList(GestorDatos.obtenerGrupos()));
            grupos.setValue(materiaActual.getGrupo());
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void regresar() {
        navegar(btnUsuario, "Información_Materia.fxml", getMensaje());
    }

    public void actualizarDatos() {
        if (!nombre.getText().isBlank()){
            Grupo nav = materiaActual.getGrupo();
            materiaActual.setGrupo(grupos.getValue());
            materiaActual.setNombre(nombre.getText().trim().toUpperCase());
            try {
                GestorDatos.actualizarMateria(materiaActual);
                navegar(btnUsuario, "Lista_Materias.fxml", nav.toHashMap());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else mensajeDeError.setVisible(true);
    }

    public void eliminar() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(NOMBRE_SISTEMA);
        alert.setHeaderText("Eliminar Materia");
        alert.setContentText("Está seguro de que quiere eliminar materia?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                GestorDatos.eliminarMateria(materiaActual);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            navegar(btnUsuario, "Lista_Materias.fxml", getMensaje());
        }
    }
}
