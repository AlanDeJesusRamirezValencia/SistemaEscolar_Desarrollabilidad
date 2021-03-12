package sample.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.calificaciones.Estudiante;
import sample.calificaciones.Grupo;
import sample.ui.arquitectura.Comunicador;

public class EditarEstudiante extends Comunicador {

    private Estudiante estudiante;

    private Grupo grupoEstudiante;

    @FXML
    private TextField nombre;

    @FXML
    private TextField apellidoPaterno;

    @FXML
    private TextField apellidoMaterno;

    @Override
    public void inicializarComponentes() {
        estudiante = Estudiante.obtenerEstudiante(getMensaje());
        nombre.setText(estudiante.getNombre());
        apellidoPaterno.setText(estudiante.getApellidoPaterno());
        apellidoMaterno.setText(estudiante.getApellidoMaterno());
        grupoEstudiante = estudiante.getGrupo();
    }

    public void crear(){
        Estudiante estudianteActualizado = new Estudiante(
                estudiante.getMatricula(),
                nombre.getText(),
                apellidoPaterno.getText(),
                apellidoMaterno.getText(),
                grupoEstudiante
        );

        //TODO: Actualizar este fragmento de código cuando el metodo exista
        //Gestor.actualizarEstudiante(estudiante, estudianteActualizado);

        navegar(nombre, "Lista_Estudiantes.fxml", getMensaje());
    }
}
