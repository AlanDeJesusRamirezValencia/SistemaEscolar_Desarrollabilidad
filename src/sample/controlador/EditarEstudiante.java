package sample.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.modelo.Estudiante;
import sample.modelo.GestorDatos;
import sample.modelo.Grupo;
import sample.controlador.arquitectura.Comunicador;

public class EditarEstudiante extends Comunicador {
    @FXML
    public Button btnUsuario;

    private Estudiante estudiante;

    private Grupo grupoEstudiante;

    @FXML
    private TextField nombre;

    @FXML
    private TextField apellidoPaterno;

    @FXML
    private TextField apellidoMaterno;

    @FXML
    public Label matricula;

    @FXML
    public Label grupo;

    @Override
    public void inicializarComponentes() {
        super.inicializarComponentes();
        estudiante = Estudiante.obtenerEstudiante(getMensaje());
        nombre.setText(estudiante.getNombre());
        apellidoPaterno.setText(estudiante.getApellidoPaterno());
        apellidoMaterno.setText(estudiante.getApellidoMaterno());
        grupoEstudiante = estudiante.getGrupo();
        matricula.setText(matricula.getText() + estudiante.getMatricula());
        grupo.setText(grupo.getText() + grupoEstudiante.toString());
    }

    public void actualizarDatos(){
        Estudiante estudianteActualizado = new Estudiante(
                estudiante.getMatricula(),
                nombre.getText(),
                apellidoPaterno.getText(),
                apellidoMaterno.getText(),
                grupoEstudiante
        );

        //TODO: Actualizar este fragmento de código cuando el metodo exista
        //GestorDatos.actualizarEstudiante(estudiante);

        navegar(nombre, "Lista_Estudiantes.fxml", getMensaje());
    }

    public void regresar() {
        navegar(nombre, "Lista_Estudiantes.fxml", getMensaje());
    }
}
