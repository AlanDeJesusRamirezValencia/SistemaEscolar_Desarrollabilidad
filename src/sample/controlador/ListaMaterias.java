
package sample.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.modelo.GestorDatos;
import sample.modelo.Grupo;
import sample.controlador.arquitectura.Comunicador;
import sample.modelo.Materia;
import sample.modelo.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;


public class ListaMaterias extends Comunicador {
    @FXML
    private VBox vMaterias;

    @FXML
    private Button btnUsuario;

    @Override
    public void inicializarComponentes() {
        Grupo grupoEstudiantes;
        ArrayList<Materia> listaMaterias   = new ArrayList<>();
        grupoEstudiantes = Grupo.obtenerGrupo(getMensaje());
        try {
            listaMaterias = GestorDatos.obtenerMaterias(grupoEstudiantes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int numeroMaterias = listaMaterias.size();
        //Determinar la lista de grupos a VBox
        Node[] nodes = new Node[numeroMaterias];
        for (int nodo = 0; nodo < nodes.length; nodo++) {
            ArrayList<Materia> listaDeMaterias = listaMaterias;
            try{
                //Obtenemos el .fxml de cada lista de estudiantes
                FXMLLoader controladorSeccion = new FXMLLoader(getClass().getResource("../vista/elementoListas/Elementos_Lista_Materias.fxml"));
                //Mostramos el contenido
                nodes[nodo] = controladorSeccion.load();
                //Obtenemos el control de cada lista
                ElementoListaMaterias lista = controladorSeccion.getController();

                Button botonInfoMaterias = new Button();

                int finalNodo = nodo;

                botonInfoMaterias.setText(listaMaterias.get(nodo).getNombre());
                botonInfoMaterias.setFont(new Font("Segoe UI", 18));
                botonInfoMaterias.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0;");
                botonInfoMaterias.setPrefHeight(20);
                botonInfoMaterias.setCursor(Cursor.HAND);
                botonInfoMaterias.setOnAction(actionEvent -> navegar(vMaterias, "Información_Materia.fxml", listaDeMaterias.get(finalNodo).toHashMap()));

                lista.contenedor.getChildren().add(botonInfoMaterias);
                vMaterias.getChildren().add(nodes[nodo]);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void regresar() {
        navegar(btnUsuario, "Información_Grupos.fxml", getMensaje());
    }
}
