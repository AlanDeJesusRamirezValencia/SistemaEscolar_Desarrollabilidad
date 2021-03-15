package sample.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.calificaciones.Estudiante;
import sample.calificaciones.Gestor;
import sample.calificaciones.Grupo;
import sample.ui.arquitectura.Comunicador;

import java.sql.SQLException;
import java.util.ArrayList;


public class ListaEstudiantes extends Comunicador {
    @FXML
    private VBox vEstudiantes;

    @Override
    public void inicializarComponentes() {
        Grupo grupoEstudiantes;
        ArrayList<Estudiante> listaEstudiantes   = new ArrayList<>();
        grupoEstudiantes = Grupo.obtenerGrupo(getMensaje());
        try {
            listaEstudiantes = Gestor.obtenerEstudiantes(grupoEstudiantes);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int numeroEstudiantes = listaEstudiantes.size();
        //Determinar la lista de grupos a VBox
        Node[] nodes = new Node[numeroEstudiantes];
        for (int nodo = 0; nodo < nodes.length; nodo++) {
            ArrayList<Estudiante> listaDeEstudiantes = listaEstudiantes;
            try{
                //Obtenemos el .fxml de cada lista de estudiantes
                FXMLLoader controladorSeccion = new FXMLLoader(getClass().getResource("../recursos/elementoListas/Elementos_Lista_Estudiantes.fxml"));
                //Mostramos el contenido
                nodes[nodo] = controladorSeccion.load();
                //Obtenemos el control de cada lista
                ElementoListaEstudiantes lista = controladorSeccion.getController();

                Button botonInfoEstudiante = new Button();

                int finalNodo = nodo;

                botonInfoEstudiante.setText(listaEstudiantes.get(nodo).getMatricula() + "\t" + listaEstudiantes.get(nodo).getApellidoPaterno() + " "
                        + listaEstudiantes.get(nodo).getApellidoMaterno() + " "
                        + listaEstudiantes.get(nodo).getNombre());
                botonInfoEstudiante.setFont(new Font("Segoe UI", 18));
                botonInfoEstudiante.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0;");
                botonInfoEstudiante.setPrefHeight(20);
                botonInfoEstudiante.setCursor(Cursor.HAND);
                botonInfoEstudiante.setOnAction(actionEvent -> navegar(vEstudiantes, "Información_Estudiante.fxml", listaDeEstudiantes.get(finalNodo).toHashMap()));

                lista.contenedor.getChildren().add(botonInfoEstudiante);
                vEstudiantes.getChildren().add(nodes[nodo]);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
