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
    private ArrayList<Calificacion> listaCalificaciones;

    @Override
    public void inicializarComponentes() {
        listaCalificaciones = new ArrayList<>();
        calificaciones = new ArrayList<>();
        materia = Materia.obtenerMateria(getMensaje());

        System.out.println("Name: " +materia.getNombre() + "/" + materia.getNrc() + "/" + materia.getGrupo());

        Grupo grupoEstudiantes;

        listaEstudiantes = new ArrayList<>();
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
            try{
                FXMLLoader controladorSeccion = new FXMLLoader(getClass().getResource("../vista/elementoListas/Elemento_Lista_Estudiantes.fxml"));
                nodes[nodo] = controladorSeccion.load();
                ElementoListaEstudiantes elementoEstudiante = controladorSeccion.getController();

                elementoEstudiante.informacionEstudiante.setText(listaEstudiantes.get(nodo).getMatricula() + "\t\t" + listaEstudiantes.get(nodo).getApellidoPaterno() + " "
                        + listaEstudiantes.get(nodo).getApellidoMaterno() + " "
                        + listaEstudiantes.get(nodo).getNombre());
                vInformacionEstudiante.getChildren().add(nodes[nodo]);


                FXMLLoader controladorSeccionCalificacion = new FXMLLoader(getClass().getResource("../vista/elementoListas/Elemento_Lista_Calificaciones.fxml"));
                nodes[nodo] = controladorSeccionCalificacion.load();
                ElementoListaCalificaciones calificacion = controladorSeccionCalificacion.getController();

                if(listaCalificaciones.isEmpty())
                    calificacion.tFCalificacion.setPromptText("N/A");
                else
                    calificacion.tFCalificacion.setText(listaCalificaciones.get(nodo).getNota() + "");

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
            califacionesObtenidas.put(listaEstudiantes.get(nodo), Integer.parseInt(calificaciones.get(nodo).tFCalificacion.getText().trim()));
        }

        GestorDatos.subirCalificaciones(califacionesObtenidas, materia, listaCalificaciones.isEmpty());

        navegar(btnUsuario, "Información_Materia.fxml", getMensaje());

    }

    public void regresar() {
        navegar(btnUsuario, "Información_Materia.fxml", getMensaje());
    }
}
