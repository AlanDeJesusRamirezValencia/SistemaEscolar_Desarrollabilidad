package sample.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.controlador.arquitectura.Comunicador;
import sample.modelo.Materia;
import sample.modelo.Usuario;

public class EditarMateria extends Comunicador {

    @FXML
    public TextField nombre;

    @FXML
    public Label grupo;

    @FXML
    public Label nrc;

    private Materia materia;

    @Override
    public void inicializarComponentes() {
        super.inicializarComponentes();
        materia = Materia.obtenerMateria(getMensaje());
        nombre.setText(materia.getNombre());
        grupo.setText(grupo.getText() + " " + materia.getGrupo());
        nrc.setText(grupo.getText() + "" + materia.getNrc());
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void regresar() {
        navegar(btnUsuario, "Información_Materia.fxml", getMensaje());
    }

    public void actualizarDatos() {
        //TODO:Actualizar materia
    }
}
