package sample.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.modelo.Materia;
import sample.modelo.Usuario;
import sample.controlador.arquitectura.Comunicador;

public class InformaciónMateria extends Comunicador {

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
    //TODO: cómo obtengo el nombre del profesor?


    @Override
    public void inicializarComponentes() {
        Materia materia = Materia.obtenerMateria(getMensaje());
        nombreMateria.setText(materia.getNombre());
        nrc.setText(materia.getNrc() + "");
        nombreGrupo.setText(materia.getGrupo().getGrado() + materia.getGrupo().getLetra() + "");
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void asignarCalificaciones(){
        //TODO: cómo asigno calificaciones?
    }

    public void exportarCalificaciones(){
        //TODO: cómo exporto calificaciones?
    }

    public void editar(){
        navegar(btnUsuario,"Editar_Materia.xml", getMensaje());
    }

}
