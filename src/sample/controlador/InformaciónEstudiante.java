package sample.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.modelo.Estudiante;
import sample.modelo.Usuario;
import sample.controlador.arquitectura.Comunicador;

public class InformaciónEstudiante extends Comunicador {

    private Estudiante estudiante;

    @FXML
    private Label nombreEstudiante;

    @FXML
    private Label matricula;

    @FXML
    private Label nombreGrupo;

    @FXML
    private Button btnUsuario;

    @Override
    public void inicializarComponentes() {
        estudiante = Estudiante.obtenerEstudiante(getMensaje());
        nombreEstudiante.setText(estudiante.toString());
        matricula.setText(estudiante.getMatricula());
        nombreGrupo.setText(estudiante.getGrupo().toString());
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void editar(){
        navegar(matricula,"Editar_Estudiante.fxml", getMensaje());
    }

    public void exportarCalificaciones(){
        new Excel(btnUsuario).crearExcel(estudiante);
    }

    public void regresar() {
        navegar(nombreEstudiante, "Lista_Estudiantes.fxml", getMensaje());
    }
}
