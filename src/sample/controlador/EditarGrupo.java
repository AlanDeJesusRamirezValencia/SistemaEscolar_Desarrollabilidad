package sample.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.modelo.Grupo;
import sample.modelo.Usuario;
import sample.controlador.arquitectura.Comunicador;

public class EditarGrupo extends Comunicador {

    @FXML
    public TextField grado;

    @FXML
    public TextField letra;

    @FXML
    public Label grupo;

    @Override
    public void inicializarComponentes() {
        super.inicializarComponentes();
        Grupo grupoActual = Grupo.obtenerGrupo(getMensaje());
        grado.setText(grupoActual.getGrado() + "");
        letra.setText(grupoActual.getLetra() + "");
        grupo.setText(grupo.getText() + " " + grupoActual.getId());
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void actualizarDatos() {

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
}
