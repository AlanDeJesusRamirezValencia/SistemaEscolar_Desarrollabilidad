package sample.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.controlador.arquitectura.Comunicador;
import sample.modelo.GestorDatos;
import sample.modelo.Profesor;
import sample.modelo.Usuario;

import java.sql.SQLException;

public class NuevoProfesor extends Comunicador {

    @FXML
    public TextField nombre;

    @FXML
    public TextField apellidoPaterno;

    @FXML
    public TextField apellidoMaterno;

    @Override
    public void inicializarComponentes() {
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void crear() {
        try {
            GestorDatos.insertarProfesor( new Profesor(0,nombre.getText(),apellidoPaterno.getText(),apellidoMaterno.getText()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        navegar(nombre, "Lista_Grupos.fxml");
    }

    public void regresar() {
        navegar(nombre, "Lista_Grupos.fxml");
    }
}
