package sample.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.calificaciones.Estudiante;
import sample.calificaciones.Usuario;
import sample.ui.arquitectura.Comunicador;

public class InformaciónEstudiante extends Comunicador {

    @FXML
    private Label nombreEstudiante;

    @FXML
    private Label matricula;

    @FXML
    private Label nombreGrupo;

    @FXML
    private Button usuario;

    @Override
    public void inicializarComponentes() {
        Estudiante estudiante = Estudiante.obtenerEstudiante(getMensaje());
        nombreEstudiante.setText(
                estudiante.getNombre() + " " +
                estudiante.getApellidoPaterno() + " " +
                estudiante.getApellidoMaterno()
        );
        matricula.setText(estudiante.getMatricula());
        nombreGrupo.setText(estudiante.getGrupo().toString());
        usuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void editar(){
        navegar(matricula,"Editar_Estudiante.fxml", getMensaje());
    }
}
