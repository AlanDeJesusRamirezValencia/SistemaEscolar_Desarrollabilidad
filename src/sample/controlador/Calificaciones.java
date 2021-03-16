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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;


public class Calificaciones extends Comunicador {
    @FXML
    private VBox vGrupos;

    public void nuevo(){

    }

    @Override
    public void inicializarComponentes() {
        ArrayList<Grupo> listaGrupos = new ArrayList<>();
        try {
            listaGrupos = GestorDatos.obtenerGrupos();
            Collections.sort(listaGrupos);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int numeroGrados = gradoMaximo(listaGrupos);

        //Determinar la lista de grupos a VBox
        Node[] nodes = new Node[numeroGrados];

        //Instancia los botones a crear
        for (int nodo = 0; nodo < nodes.length; nodo++) {
            try{
                //Obtenemos el .fxml de cada lista de grupos/ grado
                FXMLLoader controladorSeccion = new FXMLLoader(getClass().getResource("../vista/elementoListas/Elemento_Lista_Grupos.fxml"));
                //Mostramos el contenido
                nodes[nodo] = controladorSeccion.load();

                //Obtenemos el control de cada lista
                ElementoListaGrupos lista = controladorSeccion.getController();
                lista.gradoGrupo.setText( (nodo + 1) + "° Grado");

                //Se crean los botones de manera automática
                ArrayList<Grupo> listaDeGruposPorGrado = busquedaGrupos(listaGrupos, nodo+1);
                for (int nodoBotones = 0; nodoBotones < listaDeGruposPorGrado.size() ; nodoBotones++) {
                    Button botonGrupo = new Button();
                    botonGrupo.setLayoutX((nodoBotones*40) + (10*nodoBotones));
                    botonGrupo.setLayoutY(32);
                    botonGrupo.setPrefWidth(40);
                    botonGrupo.setPrefHeight(40);
                    botonGrupo.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 1; -fx-border-color: #000000;");
                    String letra = String.valueOf(listaDeGruposPorGrado.get(nodoBotones).getLetra());
                    botonGrupo.setText(letra);
                    botonGrupo.setFont(new Font("Segoe UI", 18));
                    botonGrupo.setCursor(Cursor.HAND);
                    int finalI = nodoBotones;
                    botonGrupo.setOnAction(event -> navegar(vGrupos, "Información_Grupos.fxml", listaDeGruposPorGrado.get(finalI).toHashMap()));
                    lista.contenedor.getChildren().add(botonGrupo);
                }
                vGrupos.getChildren().add(nodes[nodo]);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public int gradoMaximo(ArrayList<Grupo> grupos){
        //Extraer el grado máximo
        int contadorGrados, maximo;
        contadorGrados = maximo = 0;
        do{
            if (contadorGrados < grupos.size()){
                if(maximo < grupos.get(contadorGrados).getGrado())
                {
                    maximo = grupos.get(contadorGrados).getGrado();
                }
            } else {
                break;
            }
            contadorGrados++;
        } while(true);

        return maximo;
    }

    public ArrayList<Grupo> busquedaGrupos(ArrayList<Grupo> grupos,int buscador){
        ArrayList<Grupo> grupo = new ArrayList<>();
        int contadorGrados = 0, contadorGrupo = 0;
        //Extraer grupos de un grado especifico
        do{
            if(contadorGrados < grupos.size()){
                if(grupos.get(contadorGrados).getGrado() == buscador) {
                    grupo.add(contadorGrupo, grupos.get(contadorGrados));
                    contadorGrupo++;
                }
            } else {
                break;
            }
            contadorGrados++;
        } while(true);
        return grupo;
    }
}