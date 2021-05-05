package sample.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import sample.controlador.arquitectura.Comunicador;
import sample.modelo.GestorDatos;
import sample.modelo.Grupo;
import sample.modelo.Usuario;

import java.sql.SQLException;
import java.util.Optional;

public class EditarProfesor extends Comunicador {

    public Grupo grupo;

    @FXML
    public TextField nombre;

    @FXML
    public TextField apellidoPaterno;

    @FXML
    public TextField apellidoMaterno;

    @Override
    public void inicializarComponentes() {
        grupo = Grupo.obtenerGrupo(getMensaje());
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
        nombre.setText(grupo.getProfesor().getNombre());
        apellidoPaterno.setText(grupo.getProfesor().getApellidoPaterno());
        apellidoMaterno.setText(grupo.getProfesor().getApellidoMaterno());
    }

    public void actualizarDatos() {
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
                GestorDatos.eliminarProfesor(grupo.getProfesor());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            navegar(btnUsuario, "Lista_Grupos.fxml", getMensaje());
        }
    }
}
