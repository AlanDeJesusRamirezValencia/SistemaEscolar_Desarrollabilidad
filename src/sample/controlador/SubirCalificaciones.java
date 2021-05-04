package sample.controlador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import sample.modelo.*;
import sample.controlador.arquitectura.Comunicador;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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
    private ArrayList<String> errores;
    private final HashMap<Estudiante, Integer> califacionesObtenidas = new HashMap<>();
    private Boolean estadoCalificaciones;

    @Override
    public void inicializarComponentes() {
        listaCalificaciones = new ArrayList<>();
        calificaciones = new ArrayList<>();
        errores = new ArrayList<>();
        materia = Materia.obtenerMateria(getMensaje());

        System.out.println("Name: " +materia.getNombre() + "/" + materia.getNrc() + "/" + materia.getGrupo());


        listaEstudiantes = new ArrayList<>();
        Grupo grupoEstudiantes = Grupo.obtenerGrupo(getMensaje());
        try {
            listaEstudiantes = GestorDatos.obtenerEstudiantes(grupoEstudiantes);
            listaCalificaciones = GestorDatos.obtenerCalificaciones(materia);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        estadoCalificaciones = !listaCalificaciones.isEmpty();

        int numeroEstudiantes = listaEstudiantes.size();

        //Determinar la lista de grupos a VBox
        nodes  = new Node[numeroEstudiantes];

        for (int nodo = 0; nodo < nodes.length; nodo++) {
            try{
                //Test Lista Estudiantes y Lista Calificaciones
                System.out.println("Estudiante No. " + nodo + "\t" + listaEstudiantes.get(nodo).getMatricula() + "\t\t" + listaEstudiantes.get(nodo).getApellidoPaterno() + " "
                        + listaEstudiantes.get(nodo).getApellidoMaterno() + " "
                        + listaEstudiantes.get(nodo).getNombre());
                //TODO: Me marcan errores 2/5/2021. Corregir
                if(estadoCalificaciones) {
                    System.out.println("Calificacion Estudiante No. " + nodo + "\t" + listaCalificaciones.get(nodo).getEstudiante().getMatricula() + "\t\t" + listaCalificaciones.get(nodo).getEstudiante().getApellidoPaterno() + " "
                            + listaCalificaciones.get(nodo).getEstudiante().getApellidoMaterno() + " "
                            + listaCalificaciones.get(nodo).getEstudiante().getNombre() + "\n");
                }


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
                else {
                    calificacion.tFCalificacion.setText(listaCalificaciones.get(nodo).getNota() + "");
                }
                this.calificaciones.add(calificacion);
                vAsignarCalificaciones.getChildren().add(nodes[nodo]);

            } catch(Exception e){
                e.printStackTrace();
            }
        }
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    //Validará que los campos contengan la información correcta
    public void validar(){
        errores.clear();
        for (int nodo = 0; nodo < nodes.length; nodo++) {
            String calificacion = calificaciones.get(nodo).tFCalificacion.getText().trim();
            if (calificacion.isEmpty()){
                errores.add("Campos vacíos.");
                return;
            } else {
                if (!(Objects.equals(calificacion, "5") | calificacion.equals("6")
                        | calificacion.equals("7") | calificacion.equals("8") | calificacion.equals("9") | calificacion.equals("10"))){
                    errores.add("Valor \"" + calificacion + "\" es invalido");
                    return;
                }
            }
        }
    }


    //Guardará los datos pero solo sí la validación de campos está correcta.
    public void guardarCalificaciones() {
        System.out.println("Guardar Datos");
        validar();
        if (errores.size() > 0){
            mostrarVentanaError();
            return;
        }

        //Guardar los datos introducidos ya validados
        for (int nodo = 0; nodo < nodes.length; nodo++) {
            califacionesObtenidas.put(listaEstudiantes.get(nodo), Integer.parseInt(calificaciones.get(nodo).tFCalificacion.getText().trim()));
        }

        GestorDatos.subirCalificaciones(califacionesObtenidas, materia, estadoCalificaciones);
        System.out.println(estadoCalificaciones);
        navegar(btnUsuario, "Información_Materia.fxml", getMensaje());
    }

    //Carga la pantalla de error
    public void mostrarVentanaError(){
        StringBuilder cadenaErrores = new StringBuilder();
        for (String errore : errores) cadenaErrores.append(errore).append("\n");
        Alert mensajeError = new Alert(Alert.AlertType.ERROR);
        mensajeError.setTitle("Error");
        mensajeError.setHeaderText("Se encontraron los siguientes errores.");
        mensajeError.setContentText(cadenaErrores.toString());
        mensajeError.show();
    }

    //TODO Checar que me puede servir y si no borrarlo
    public void guardarCalificacionesOriginal() {
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
