package sample.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.calificaciones.Grupo;
import sample.calificaciones.Usuario;
import sample.ui.arquitectura.Comunicador;


public class InformaciónGrupo extends Comunicador {

    private Grupo grupo;

    @FXML
    private Label nombreGrupo;

    @FXML
    private Button usuario;

    @Override
    public void inicializarComponentes(){
        grupo = Grupo.obtenerGrupo(getMensaje());
        nombreGrupo.setText(grupo.getGrado() + "" + grupo.getLetra());
        usuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void irAProfesor(){
        navegar(usuario, "Información_Profesor.fxml", grupo.toHashMap());
    }

    public void irAMaterias(){
        navegar(usuario, "Lista_Materias.fxml",  grupo.toHashMap());
    }

    public void irAEstudiantes(){
        navegar(usuario, "Lista_Estudiantes.fxml", grupo.toHashMap());
    }

    public void editar(){
        navegar(usuario, "Editar_Grupo.fxml",  grupo.toHashMap());
    }
}
