package sample.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.modelo.GestorDatos;
import sample.modelo.Grupo;
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

    @Override
    public void inicializarComponentes() {
        super.inicializarComponentes();
        grupoActual = Grupo.obtenerGrupo(getMensaje());
        grado.setText(grupoActual.getGrado() + "");
        letra.setText(grupoActual.getLetra() + "");
        grupo.setText(grupo.getText() + " " + grupoActual.getId());
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void actualizarDatos() {
        //TODO: actualizar datos
        /*GestorDatos.actualizarGrupo(
                return new Grupo(
                        grupoActual.getId(),
                        Integer.parseInt(letra.getText()),
                        letra.getText().charAt(0),
                        grupoActual.getProfesor()
                )
        );*/
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
