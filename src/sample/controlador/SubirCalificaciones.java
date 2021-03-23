package sample.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.modelo.*;
import sample.controlador.arquitectura.Comunicador;

import java.sql.SQLException;
import java.util.ArrayList;

public class SubirCalificaciones extends Comunicador {

    @FXML
    private VBox vAsignarCalificaciones;
    @FXML
    public Button btnUsuario;


    @Override
    public void inicializarComponentes() {
        Materia materia;
        ArrayList<Calificacion> listaCalificaciones   = new ArrayList<>();
        materia = Materia.obtenerMateria(getMensaje());
        try {
            listaCalificaciones = GestorDatos.obtenerCalificaciones(materia);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int numeroCalificaciones = listaCalificaciones.size();
        //Determinar la lista de grupos a VBox
        Node[] nodes = new Node[numeroCalificaciones];
        for (int nodo = 0; nodo < nodes.length; nodo++) {
            ArrayList<Calificacion> listaDeCalificaciones = listaCalificaciones;
            try{
                //Obtenemos el .fxml de cada lista de estudiantes
                FXMLLoader controladorSeccion = new FXMLLoader(getClass().getResource("../vista/elementoListas/Elemento_Lista_Calificaciones.fxml"));
                //Mostramos el contenido
                nodes[nodo] = controladorSeccion.load();
                //Obtenemos el control de cada lista
                ElementoListaCalificaciones lista = controladorSeccion.getController();

                Button infoCalificacionEstudiante = new Button();

                int finalNodo = nodo;

                infoCalificacionEstudiante.setText(listaCalificaciones.get(finalNodo).getEstudiante().getMatricula());
                infoCalificacionEstudiante.setFont(new Font("Segoe UI", 18));
                infoCalificacionEstudiante.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0;");
                infoCalificacionEstudiante.setPrefHeight(20);
                //TODO Crear un botón de Guardado de toda la información.
                infoCalificacionEstudiante.setOnAction(actionEvent -> navegar(vAsignarCalificaciones, "Información_Estudiante.fxml", listaDeCalificaciones.get(finalNodo).getEstudiante().toHashMap()));

                lista.contenedor.getChildren().add(infoCalificacionEstudiante);
                vAsignarCalificaciones.getChildren().add(nodes[nodo]);
            } catch(Exception e){
                e.printStackTrace();
            }
        }


        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }
}
