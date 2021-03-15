package sample.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.calificaciones.Grupo;
import sample.calificaciones.Usuario;
import sample.ui.arquitectura.Comunicador;

public class InformaciónProfesor extends Comunicador {

    @FXML
    private Label nombreProfesor;

    @FXML
    private Label grupo;

    @FXML
    private Button btnUsuario;

    @Override
    public void inicializarComponentes() {
        Grupo grupoProfesor = Grupo.obtenerGrupo(getMensaje());
        nombreProfesor.setText(grupoProfesor.getProfesor().getApellidoPaterno() + " " + grupoProfesor.getProfesor().getApellidoMaterno() + " " + grupoProfesor.getProfesor().getNombre());
        grupo.setText(grupoProfesor.toString());
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void editar(){
        navegar(grupo,"Editar_Profesor.fxml", getMensaje());
    }
}
