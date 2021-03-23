package sample.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

        Grupo grupoEstudiantes;
        ArrayList<Estudiante> listaEstudiantes   = new ArrayList<>();
        grupoEstudiantes = Grupo.obtenerGrupo(getMensaje());

        try {
            listaEstudiantes = GestorDatos.obtenerEstudiantes(grupoEstudiantes);
            listaCalificaciones = GestorDatos.obtenerCalificaciones(materia);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int numeroCalificaciones = listaEstudiantes.size();
        //Determinar la lista de grupos a VBox
        Node[] nodes = new Node[numeroCalificaciones];
        for (int nodo = 0; nodo < nodes.length; nodo++) {
            ArrayList<Estudiante> listaDeEstudiantes = listaEstudiantes;
            try{
                //Obtenemos el .fxml de cada lista de estudiantes
                FXMLLoader controladorSeccion = new FXMLLoader(getClass().getResource("../vista/elementoListas/Elemento_Lista_Estudiantes.fxml"));
                FXMLLoader controladorSeccionCalificacion = new FXMLLoader(getClass().getResource("../vista/elementoListas/Elemento_Lista_Calificaciones.fxml"));
                //Mostramos el contenido
                nodes[nodo] = controladorSeccion.load();
                //Obtenemos el control de cada lista
                ElementoListaEstudiantes lista = controladorSeccion.getController();
                ElementoListaCalificaciones listaCalificacion = controladorSeccionCalificacion.getController();

                Button infoCalificacionEstudiante = new Button();
                Button calificacion = new Button();

                int finalNodo = nodo;


                calificacion.setText("0");

                infoCalificacionEstudiante.setText(listaEstudiantes.get(nodo).getMatricula() + "\t\t" + listaEstudiantes.get(nodo).getApellidoPaterno() + " "
                        + listaEstudiantes.get(nodo).getApellidoMaterno() + " "
                        + listaEstudiantes.get(nodo).getNombre());
                infoCalificacionEstudiante.setFont(new Font("Segoe UI", 18));
                infoCalificacionEstudiante.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0;");
                infoCalificacionEstudiante.setPrefHeight(20);
                infoCalificacionEstudiante.setCursor(Cursor.HAND);
                //TODO Crear un botón de Guardado de toda la información.
                infoCalificacionEstudiante.setOnAction(actionEvent -> navegar(vAsignarCalificaciones, "Información_Estudiante.fxml", listaDeEstudiantes.get(finalNodo).toHashMap()));

                calificacion.setText("0");
                calificacion.setFont(new Font("Segoe UI", 18));
                calificacion.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0;");
                calificacion.setOnAction(actionEvent -> System.out.println("Botón calificación activado del nodo: " + finalNodo));
                calificacion.setLayoutX(610.00);
                calificacion.setCursor(Cursor.HAND);

                lista.contenedor.getChildren().add(infoCalificacionEstudiante);
                lista.contenedor.getChildren().add(calificacion);
                vAsignarCalificaciones.getChildren().add(nodes[nodo]);
            } catch(Exception e){
                e.printStackTrace();
            }
        }


        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }
}
