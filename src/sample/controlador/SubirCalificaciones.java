package sample.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.modelo.*;
import sample.controlador.arquitectura.Comunicador;

import java.sql.SQLException;
import java.util.ArrayList;

public class SubirCalificaciones extends Comunicador {

    @FXML
    private VBox vInformacionEstudiante;
    @FXML
    private VBox vAsignarCalificaciones;
    @FXML
    public Button btnUsuario;
    @FXML
    public Button btnGuardar;

    Node[] nodes;

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

        int numeroEstudiantes = listaEstudiantes.size();
        //Determinar la lista de grupos a VBox
        nodes  = new Node[numeroEstudiantes];
        for (int nodo = 0; nodo < nodes.length; nodo++) {

            int finalNodo = nodo;
            ArrayList<Estudiante> listaDeEstudiantes = listaEstudiantes;
            try{
                //Obtenemos el .fxml de cada lista de estudiantes
                FXMLLoader controladorSeccion = new FXMLLoader(getClass().getResource("../vista/elementoListas/Elemento_Lista_Estudiantes.fxml"));
                //Mostramos el contenido
                nodes[nodo] = controladorSeccion.load();
                //Obtenemos el control de cada lista
                ElementoListaEstudiantes lista = controladorSeccion.getController();
                Button infoCalificacionEstudiante = new Button();
                infoCalificacionEstudiante.setText(listaEstudiantes.get(nodo).getMatricula() + "\t\t" + listaEstudiantes.get(nodo).getApellidoPaterno() + " "
                        + listaEstudiantes.get(nodo).getApellidoMaterno() + " "
                        + listaEstudiantes.get(nodo).getNombre());
                infoCalificacionEstudiante.setFont(new Font("Segoe UI", 18));
                infoCalificacionEstudiante.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0;");
                infoCalificacionEstudiante.setPrefHeight(20);
                infoCalificacionEstudiante.setCursor(Cursor.HAND);
                infoCalificacionEstudiante.setOnAction(actionEvent -> navegar(vInformacionEstudiante, "Información_Estudiante.fxml", listaDeEstudiantes.get(finalNodo).toHashMap()));
                lista.contenedor.getChildren().add(infoCalificacionEstudiante);
                vInformacionEstudiante.getChildren().add(nodes[nodo]);

                //Obtenemos el .fxml de cada lista de estudiantes
                FXMLLoader controladorSeccionCalificacion = new FXMLLoader(getClass().getResource("../vista/elementoListas/Elemento_Lista_Calificaciones.fxml"));
                //Mostramos el contenido
                nodes[nodo] = controladorSeccionCalificacion.load();
                //Obtenemos el control de cada lista
                ElementoListaCalificaciones listaCalificacion = controladorSeccionCalificacion.getController();
                TextField calificacion = new TextField();
                int nota = listaCalificaciones.get(nodo).getNota();
                String calificaciones = "" + nota;
                if( calificaciones.equals("")) {
                    calificacion.setText("N/A");
                } else {
                    calificacion.setText(calificaciones);
                }
                calificacion.setFont(new Font("Segoe UI", 18));
                calificacion.setAlignment(Pos.CENTER);
                calificacion.setStyle("-fx-background-color: #d0d0d0;");
                calificacion.setMaxWidth(70.00);
                calificacion.setCursor(Cursor.HAND);
                if( !calificaciones.equals("")) {
                    listaCalificacion.contenedorCalificaciones.getChildren().add(calificacion);
                }
                vAsignarCalificaciones.getChildren().add(nodes[nodo]);

            } catch(Exception e){
                e.printStackTrace();
            }

        }

        //TODO Crear un botón de Guardado de toda la información.
        btnGuardar.setOnAction(actionEvent -> System.out.println("Guardando las calificaciones..."));
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }
}
