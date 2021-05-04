package sample.controlador;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import sample.controlador.arquitectura.Comunicador;
import sample.modelo.GestorDatos;
import sample.modelo.Grupo;
import sample.modelo.Profesor;
import sample.modelo.Usuario;

import java.sql.SQLException;

public class NuevoGrupo extends Comunicador {

    @FXML
    public TextField grado;

    @FXML
    public TextField letra;

    @Override
    public void inicializarComponentes() {
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void crear() {
        //TODO: Hacer pruebas de este método
        try {
            GestorDatos.insertarGrupo(
                    new Grupo(
                            0,
                            Integer.parseInt(grado.getText()),
                            letra.getText().charAt(0),
                            new Profesor(1, "","","")
                    ),
                    true
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        navegar(grado, "Lista_Grupos.fxml");
    }

    public void regresar() {
        navegar(grado, "Lista_Grupos.fxml");
    }
}
