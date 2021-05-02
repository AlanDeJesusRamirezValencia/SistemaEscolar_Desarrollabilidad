package sample.controlador;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.modelo.Estudiante;
import sample.modelo.GestorDatos;
import sample.modelo.Grupo;
import sample.controlador.arquitectura.Comunicador;

import java.sql.SQLException;

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
    public ComboBox<Grupo> grupos;

    @Override
    public void inicializarComponentes() {
        super.inicializarComponentes();
        estudiante = Estudiante.obtenerEstudiante(getMensaje());
        nombre.setText(estudiante.getNombre());
        apellidoPaterno.setText(estudiante.getApellidoPaterno());
        apellidoMaterno.setText(estudiante.getApellidoMaterno());
        grupoEstudiante = estudiante.getGrupo();
        matricula.setText(matricula.getText() + estudiante.getMatricula());

        try {
            grupos = new ComboBox<>(FXCollections.observableArrayList(GestorDatos.obtenerGrupos()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void actualizarDatos(){
        estudiante.setNombre(nombre.getText());
        estudiante.setApellidoPaterno(apellidoPaterno.getText());
        estudiante.setApellidoMaterno(apellidoMaterno.getText());
        estudiante.setGrupo(grupoEstudiante);

        try {
            GestorDatos.actualizarEstudiante(estudiante);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        navegar(nombre, "Lista_Estudiantes.fxml", getMensaje());
    }

    public void regresar() {
        navegar(nombre, "Lista_Estudiantes.fxml", getMensaje());
    }
}
