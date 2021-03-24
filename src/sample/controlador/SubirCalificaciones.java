package sample.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import sample.modelo.*;
import sample.controlador.arquitectura.Comunicador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SubirCalificaciones extends Comunicador {

    @FXML
    private VBox vInformacionEstudiante;
    @FXML
    private VBox vAsignarCalificaciones;
    @FXML
    public Button btnUsuario;

    private Node[] nodes;
    private Materia materia;
    private ArrayList<Estudiante> listaEstudiantes;
    private ArrayList<ElementoListaCalificaciones> calificaciones;

    @Override
    public void inicializarComponentes() {
        ArrayList<Calificacion> listaCalificaciones   = new ArrayList<>();
        calificaciones = new ArrayList<>();
        materia = Materia.obtenerMateria(getMensaje());

        Grupo grupoEstudiantes;

        listaEstudiantes = new ArrayList<>();
        grupoEstudiantes = Grupo.obtenerGrupo(getMensaje());

        try {
            listaEstudiantes = GestorDatos.obtenerEstudiantes(grupoEstudiantes);
            listaCalificaciones = GestorDatos.obtenerCalificaciones(materia);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (listaCalificaciones.isEmpty()){
            listaCalificaciones = inicializarCalificaciones(listaEstudiantes,materia);
        }

        int numeroEstudiantes = listaEstudiantes.size();
        //Determinar la lista de grupos a VBox
        nodes  = new Node[numeroEstudiantes];

        for (int nodo = 0; nodo < nodes.length; nodo++) {
            try{
                FXMLLoader controladorSeccion = new FXMLLoader(getClass().getResource("../vista/elementoListas/Elemento_Lista_Estudiantes.fxml"));
                nodes[nodo] = controladorSeccion.load();

                ElementoListaEstudiantes listaEstudiante = controladorSeccion.getController();

                listaEstudiante.informacionEstudiante.setText(listaEstudiantes.get(nodo).getMatricula() + "\t\t" + listaEstudiantes.get(nodo).getApellidoPaterno() + " "
                        + listaEstudiantes.get(nodo).getApellidoMaterno() + " "
                        + listaEstudiantes.get(nodo).getNombre());
                vInformacionEstudiante.getChildren().add(nodes[nodo]);

                FXMLLoader controladorSeccionCalificacion = new FXMLLoader(getClass().getResource("../vista/elementoListas/Elemento_Lista_Calificaciones.fxml"));
                nodes[nodo] = controladorSeccionCalificacion.load();
                ElementoListaCalificaciones calificacion = controladorSeccionCalificacion.getController();
                int nota = listaCalificaciones.get(nodo).getNota();
                String calificaciones = "" + nota;
                calificacion.tFCalificacion.setText((calificaciones.isEmpty()? "N/A": calificaciones  ));
                this.calificaciones.add(calificacion);
                vAsignarCalificaciones.getChildren().add(nodes[nodo]);

            } catch(Exception e){
                e.printStackTrace();
            }

        }
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void guardarCalificaciones() {
        HashMap<Estudiante, Integer> califacionesObtenidas = new HashMap<>();
        for (int nodo = 0; nodo < nodes.length; nodo++) {
            califacionesObtenidas.put(listaEstudiantes.get(nodo), Integer.parseInt(calificaciones.get(nodo).tFCalificacion.getText()));
        }
        try {
            GestorDatos.subirCalificaciones(califacionesObtenidas, materia);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private ArrayList<Calificacion> inicializarCalificaciones(ArrayList<Estudiante> estudiantes, Materia materia){
        ArrayList<Calificacion> calificaciones = new ArrayList<>();
        for (Estudiante estudiante: estudiantes){
            calificaciones.add(new Calificacion(0, materia, estudiante));
        }
        return calificaciones;
    }
}
