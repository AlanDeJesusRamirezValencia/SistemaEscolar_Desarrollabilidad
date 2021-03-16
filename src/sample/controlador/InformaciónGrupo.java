package sample.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.modelo.Grupo;
import sample.modelo.Usuario;
import sample.controlador.arquitectura.Comunicador;


public class InformaciónGrupo extends Comunicador {

    private Grupo grupo;

    @FXML
    private Label nombreGrupo;

    @FXML
    private Button btnUsuario;

    @Override
    public void inicializarComponentes(){
        grupo = Grupo.obtenerGrupo(getMensaje());
        nombreGrupo.setText(grupo.getGrado() + "" + grupo.getLetra());
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void irAProfesor(){
        navegar(btnUsuario, "Información_Profesor.fxml", grupo.toHashMap());
    }

    public void irAMaterias(){
        navegar(btnUsuario, "Lista_Materias.fxml",  grupo.toHashMap());
    }

    public void irAEstudiantes(){
        navegar(btnUsuario, "Lista_Estudiantes.fxml", grupo.toHashMap());
    }

    public void editar(){
        navegar(btnUsuario, "Editar_Grupo.fxml",  grupo.toHashMap());
    }
}
