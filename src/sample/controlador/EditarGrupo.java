package sample.controlador;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.modelo.GestorDatos;
import sample.modelo.Grupo;
import sample.modelo.Profesor;
import sample.modelo.Usuario;
import sample.controlador.arquitectura.Comunicador;

import java.sql.SQLException;
import java.util.Optional;

public class EditarGrupo extends Comunicador {

    private Grupo grupoActual;

    @FXML
    public TextField grado;

    @FXML
    public TextField letra;

    @FXML
    public Label grupo;

    @FXML
    public Label mensajeDeError;

    @FXML
    public ComboBox<Profesor> profesores;

    @Override
    public void inicializarComponentes() {
        super.inicializarComponentes();
        grupoActual = Grupo.obtenerGrupo(getMensaje());
        grado.setText(grupoActual.getGrado() + "");
        letra.setText(grupoActual.getLetra() + "");
        grupo.setText(grupo.getText() + " " + grupoActual.getId());
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
        try {
            profesores.setItems(FXCollections.observableArrayList(GestorDatos.obtenerProfesores()));
            profesores.setValue(grupoActual.getProfesor());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void actualizarDatos() {
        if (!grado.getText().isBlank() && !letra.getText().isBlank()) {
            grupoActual.setGrado(Integer.parseInt(grado.getText()));
            grupoActual.setLetra(letra.getText().charAt(0));
            grupoActual.setProfesor(profesores.getValue());
            try {
                GestorDatos.actualizarGrupo(grupoActual);
                navegar(grupo, "Información_Grupos.fxml", grupoActual.toHashMap());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else
            mensajeDeError.setVisible(true);
    }

    public void regresar() {
        navegar(grupo, "Información_Grupos.fxml", getMensaje());
    }

    public void eliminar() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(NOMBRE_SISTEMA);
        alert.setHeaderText("Eliminar Grupo");
        alert.setContentText("Está seguro de que quiere eliminar grupo?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                GestorDatos.eliminarGrupo(grupoActual);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            navegar(btnUsuario, "Lista_Grupos.fxml", getMensaje());
        }
    }
}
