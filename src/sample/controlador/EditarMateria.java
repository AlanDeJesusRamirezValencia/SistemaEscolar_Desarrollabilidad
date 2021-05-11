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

    private Materia materia;

    @FXML
    private ComboBox<Grupo> grupos;

    @FXML
    public Label mensajeDeError;

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
        if (grupos.getValue() == null || nombre.getText().trim().equals("")){
            materia.setGrupo(grupos.getValue());
            materia.setNombre(nombre.getText());
            try {
                GestorDatos.actualizarMateria(materia);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else
            mensajeDeError.setVisible(true);


    }

    public void eliminar() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(NOMBRE_SISTEMA);
        alert.setHeaderText("Eliminar Materia");
        alert.setContentText("Está seguro de que quiere eliminar materia?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                GestorDatos.eliminarMateria(materia);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            navegar(btnUsuario, "Lista_Materias.fxml", getMensaje());
        }
    }
}
