package sample.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.modelo.Grupo;
import sample.modelo.Profesor;
import sample.modelo.Usuario;
import sample.controlador.arquitectura.Comunicador;

public class InformaciónProfesor extends Comunicador {

    private Profesor profesor;

    @FXML
    private Label nombreProfesor;

    @FXML
    private Label grupo;

    @FXML
    private Button btnUsuario;

    @Override
    public void inicializarComponentes() {
        Grupo grupoProfesor = Grupo.obtenerGrupo(getMensaje());
        profesor = grupoProfesor.getProfesor();
        nombreProfesor.setText(profesor.toString());
        grupo.setText(grupoProfesor.toString());
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void editar(){
        navegar(grupo,"Editar_Profesor.fxml", getMensaje());
    }

    public void regresar() {
        navegar(nombreProfesor, "Información_Grupos.fxml", getMensaje());
    }
}
