package sample.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.calificaciones.Gestor;
import sample.calificaciones.Grupo;
import sample.calificaciones.Usuario;
import sample.ui.arquitectura.Comunicador;

public class EditarGrupo extends Comunicador {
    private Grupo grupoActual;

    @FXML
    public TextField grado;

    @FXML
    public TextField letra;

    @FXML
    public Label grupo;

    @FXML
    public Button usuario;

    @Override
    public void inicializarComponentes() {
        grupoActual = Grupo.obtenerGrupo(getMensaje());
        grado.setText(grupoActual.getGrado() + "");
        letra.setText(grupoActual.getLetra() + "");
        grupo.setText(grupoActual.getId() + "");
        usuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void actualizarDatos() {

        /*Gestor.actualizarGrupo(
                return new Grupo(
                        grupoActual.getId(),
                        Integer.parseInt(letra.getText()),
                        letra.getText().charAt(0),
                        grupoActual.getProfesor()
                )
        );*/
    }
}
