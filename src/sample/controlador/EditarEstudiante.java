package sample.controlador;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.modelo.Estudiante;
import sample.modelo.GestorDatos;
import sample.modelo.Grupo;
import sample.controlador.arquitectura.Comunicador;

import java.sql.SQLException;
import java.util.Optional;

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
    public Label mensajeDeError;


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
        grupos.setValue(Grupo.obtenerGrupo(getMensaje()));
        try {
            grupos.setItems(FXCollections.observableArrayList(GestorDatos.obtenerGrupos()));
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void actualizarDatos(){
        if (!nombre.getText().equals("") && nombre.getText().equals("") && !apellidoMaterno.getText().equals("")){
            estudiante.setNombre(nombre.getText());
            estudiante.setApellidoPaterno(apellidoPaterno.getText());
            estudiante.setApellidoMaterno(apellidoMaterno.getText());
            estudiante.setGrupo(grupoEstudiante);
            try {
                GestorDatos.actualizarEstudiante(estudiante);
                navegar(nombre, "Lista_Estudiantes.fxml", getMensaje());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else
            mensajeDeError.setVisible(true);
    }

    public void regresar() {
        navegar(nombre, "Lista_Estudiantes.fxml", getMensaje());
    }

    public void eliminar() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(NOMBRE_SISTEMA);
        alert.setHeaderText("Eliminar Estudiante");
        alert.setContentText("Está seguro de que quiere eliminar estudiante?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                GestorDatos.eliminarEstudiante(estudiante);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            navegar(nombre, "Lista_Estudiantes.fxml", getMensaje());
        }
    }
}
