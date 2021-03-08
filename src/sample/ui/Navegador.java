package sample.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface Navegador {
    String DIRECTORIO_DE_ESCENARIOS = "../recursos/escenarios/";
    String NOMBRE_SISTEMA = "Systema Escolar";
    int ANCHO_PREDEF = 800;
    int ALTO_PREDEF = 600;

    default void navegar(Node componenteUI, String archivoFXML, String nombreVentana){
        try {
            Stage stageActual = (Stage) componenteUI.getScene().getWindow();
            Parent nuevaEscena = FXMLLoader.load(getClass().getResource(DIRECTORIO_DE_ESCENARIOS + archivoFXML));
            stageActual.setTitle( NOMBRE_SISTEMA + " - " + nombreVentana);
            stageActual.setScene(new Scene(nuevaEscena, ANCHO_PREDEF, ALTO_PREDEF));
            stageActual.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    default void navegarMain(Stage stageActual, String archivoFXML){
        try {
            Parent nuevaEscena = FXMLLoader.load(getClass().getResource("recursos/escenarios/" + archivoFXML));
            stageActual.setTitle(NOMBRE_SISTEMA);
            stageActual.setScene(new Scene(nuevaEscena, ANCHO_PREDEF, ALTO_PREDEF));
            stageActual.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
