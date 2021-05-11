package sample.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.modelo.GestorDatos;
import sample.modelo.Materia;
import sample.modelo.Usuario;
import sample.controlador.arquitectura.Comunicador;

import java.sql.SQLException;

public class InformaciónMateria extends Comunicador {

    private Materia materia;

    @FXML
    public Button btnUsuario;

    @FXML
    private Label nombreMateria;

    @FXML
    private Label nrc;

    @FXML
    private Label nombreGrupo;

    @FXML
    private Label nombreProfesor;


    @Override
    public void inicializarComponentes() {
        materia = Materia.obtenerMateria(getMensaje());
        nombreMateria.setText(materia.getNombre());
        nrc.setText(materia.getNrc() + "");
        nombreGrupo.setText(materia.getGrupo().toString());
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
        try {
            nombreProfesor.setText(GestorDatos.obtenerProfesor(materia.getGrupo()).toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void asignarCalificaciones(){ navegar(nombreMateria, "Subir_Calificaciones.fxml", materia.toHashMap()); }

    public void exportarCalificaciones(){
        new GeneradorExcel(btnUsuario).crearExcel(materia);
    }

    public void editar(){
        navegar(nombreGrupo,"Editar_Materia.fxml", getMensaje());
    }

    public void regresar() {
        navegar(nombreGrupo, "Lista_Materias.fxml", getMensaje());
    }
}
