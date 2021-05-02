package sample.controlador;

import javafx.event.ActionEvent;
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
import sample.modelo.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaGrupos extends Comunicador {

    @FXML
    public Button btnNuevoGrupo;

    @FXML
    public Button btnNuevaMateria;

    @FXML
    public Button btnNuevoProfesor;

    @FXML
    public Button btnNuevoEstudiante;

    @FXML
    private VBox vGrupos;

    @FXML
    private Button btnUsuario;

    public void nuevo(){
        btnNuevoGrupo.setVisible(!btnNuevoGrupo.isVisible());
        btnNuevaMateria.setVisible(!btnNuevaMateria.isVisible());
        btnNuevoEstudiante.setVisible(!btnNuevoEstudiante.isVisible());
        btnNuevoProfesor.setVisible(!btnNuevoProfesor.isVisible());
    }

    @Override
    public void inicializarComponentes() {
        ArrayList<Grupo> grupos = new ArrayList<>();
        try {
            grupos = GestorDatos.obtenerGrupos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int NUMERO_GRADOS = 6;

        //Determinar la lista de grupos a VBox
        Node[] nodes = new Node[NUMERO_GRADOS];

        //Instancia los botones a crear
        for (int nodo = 0; nodo < nodes.length; nodo++) {
            try{
                //Obtenemos el .fxml de cada lista de grupos/ grado
                FXMLLoader controladorSeccion = new FXMLLoader(getClass().getResource("../vista/elementoListas/Elemento_Lista_Grupos.fxml"));
                //Mostramos el contenido
                nodes[nodo] = controladorSeccion.load();

                //Obtenemos el control de cada lista
                ElementoListaGrupos lista = controladorSeccion.getController();
                lista.gradoGrupo.setText( (nodo+1) + "° Grado");

                //Se crean los botones de manera automática
                ArrayList<Grupo> listaDeGruposPorGrado = new ArrayList<>();
                for (Grupo g: grupos){
                    if (g.getGrado() == nodo+1){
                        listaDeGruposPorGrado.add(g);
                    }
                }
                for (int nodoBotones = 0; nodoBotones < listaDeGruposPorGrado.size() ; nodoBotones++) {
                    Button btnGrupo = new Button();
                    btnGrupo.setLayoutX((nodoBotones*40) + (10*nodoBotones));
                    btnGrupo.setLayoutY(32);
                    btnGrupo.setPrefWidth(40);
                    btnGrupo.setPrefHeight(40);
                    btnGrupo.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 1; -fx-border-color: #000000;");
                    String letra = String.valueOf(listaDeGruposPorGrado.get(nodoBotones).getLetra());
                    btnGrupo.setText(letra);
                    btnGrupo.setFont(new Font("Segoe UI", 18));
                    btnGrupo.setCursor(Cursor.HAND);
                    int finalI = nodoBotones;
                    btnGrupo.setOnAction(event -> {
                        try {
                            GestorDatos.obtenerProfesor(listaDeGruposPorGrado.get(finalI));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        navegar(vGrupos, "Información_Grupos.fxml", listaDeGruposPorGrado.get(finalI).toHashMap());
                    });
                    lista.contenedor.getChildren().add(btnGrupo);
                }
                vGrupos.getChildren().add(nodes[nodo]);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        btnUsuario.setText(Usuario.obtenerUsuario(getMensaje()));
    }

    public void regresar() {
        navegar(btnUsuario, "Información_Grupos.fxml", getMensaje());
    }

    public void irANuevoGrupo() {
        navegar(btnUsuario, "Nuevo_Grupo.fxml");
    }

    public void irANuevaMateria() {
        navegar(btnUsuario, "Nuevo_Grupo.fxml");
    }

    public void irANuevoProfesor() {
        navegar(btnUsuario, "Nuevo_Grupo.fxml");
    }

    public void irANuevoEstudiante() {
        navegar(btnUsuario, "Nuevo_Estudiante.fxml");
    }
}
