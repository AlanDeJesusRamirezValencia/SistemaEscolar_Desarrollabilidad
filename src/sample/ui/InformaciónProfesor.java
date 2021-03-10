package sample.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.calificaciones.Profesor;
import sample.calificaciones.Usuario;
import sample.ui.arquitectura.Comunicador;

public class InformaciónProfesor extends Comunicador {

    @FXML
    private Label nombreProfesor;

    @FXML
    private Label grupo;

    @FXML
    private Button usuario;

    @Override
    public void inicializarComponentes() {
        Profesor profesor = Profesor.obtenerProfesor(getMensaje());
        nombreProfesor.setText(profesor.getNombre() + profesor.getApellidoPaterno() + profesor.getApellidoMaterno());
        grupo.setText(profesor.getGrupo().toString());
        usuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void editar(){
        navegar(grupo,"Editar_Profesor.fxml", getMensaje());
    }
}
