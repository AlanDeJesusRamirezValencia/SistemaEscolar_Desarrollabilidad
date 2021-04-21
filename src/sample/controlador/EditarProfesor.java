package sample.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.controlador.arquitectura.Comunicador;
import sample.modelo.Grupo;
import sample.modelo.Usuario;

public class EditarProfesor extends Comunicador {

    public Grupo grupo;

    @FXML
    public TextField nombre;

    @FXML
    public TextField apellidoPaterno;

    @FXML
    public TextField apellidoMaterno;

    @Override
    public void inicializarComponentes() {
        grupo = Grupo.obtenerGrupo(getMensaje());
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
        nombre.setText(grupo.getProfesor().getNombre());
        apellidoPaterno.setText(grupo.getProfesor().getApellidoPaterno());
        apellidoMaterno.setText(grupo.getProfesor().getApellidoMaterno());
    }

    public void actualizarDatos() {
    }

    public void regresar() {
        navegar(nombre, "Información_Profesor.fxml", getMensaje());
    }
}
