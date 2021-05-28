package sample.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.controlador.arquitectura.Comunicador;
import sample.modelo.GestorDatos;
import sample.modelo.Grupo;
import sample.modelo.Profesor;
import sample.modelo.Usuario;

import java.sql.SQLException;
import java.util.Optional;

public class EditarProfesor extends Comunicador {

    public Profesor profesorActual;

    @FXML
    public TextField nombre;

    @FXML
    public TextField apellidoPaterno;

    @FXML
    public TextField apellidoMaterno;

    @FXML
    public Label mensajeDeError;

    @Override
    public void inicializarComponentes() {
        profesorActual = Grupo.obtenerGrupo(getMensaje()).getProfesor();
        btnUsuario.setText(Usuario.obtenerUsuario((getMensaje())));
        nombre.setText(profesorActual.getNombre());
        apellidoPaterno.setText(profesorActual.getApellidoPaterno());
        apellidoMaterno.setText(profesorActual.getApellidoMaterno());
    }

    public void actualizarDatos() {
        if(!nombre.getText().isBlank() && !apellidoMaterno.getText().isBlank() && !apellidoPaterno.getText().isBlank()){
            profesorActual.setNombre(nombre.getText().toUpperCase().trim());
            profesorActual.setApellidoPaterno(apellidoPaterno.getText().toUpperCase().trim());
            profesorActual.setApellidoMaterno(apellidoMaterno.getText().toUpperCase().trim());
            try {
                GestorDatos.actualizarProfesor(profesorActual);
                Grupo grupoActual = Grupo.obtenerGrupo(getMensaje());
                grupoActual.setProfesor(profesorActual);
                navegar(nombre, "Información_Profesor.fxml", grupoActual.toHashMap());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else mensajeDeError.setVisible(true);
    }

    public void regresar() {
        navegar(nombre, "Información_Profesor.fxml", getMensaje());
    }

    public void eliminar() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(NOMBRE_SISTEMA);
        alert.setHeaderText("Eliminar Profesor");
        alert.setContentText("Está seguro de que quiere eliminar profesor?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                GestorDatos.eliminarProfesor(profesorActual);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            navegar(btnUsuario, "Lista_Grupos.fxml", getMensaje());
        }
    }
}
