package sample.ui.arquitectura;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

/**
 * Interfaz para la navegación entre escenarios
 *
 * Permite una transición sencilla entre escenarios. Debe ser implementado
 * por los controladores de los archivos .fxml
 *
 * @author alexter
 * @since 09/03/2021
 * @version 1.0
 */
public interface Navegador  {

    /** Es la dirección de los recursos .fxml */
    String DIRECTORIO_DE_ESCENARIOS = "../recursos/escenarios/";

    /**Nombre del sistema*/
    String NOMBRE_SISTEMA = "Systema Escolar";

    /** Ancho por defecto del {@link Stage}*/
    int ANCHO_PREDEF = 800;

    /** Alto por defecto del {@link Stage}*/
    int ALTO_PREDEF = 600;

    /**
     * Metodo principal de navegación entre escenarios.
     *
     * Este método se encarga de cambiar de un escenario a otro,
     * asegurandose de su comunicación.
     *
     * @param componenteUI Cualquier objeto identficado en el escenario actual que herede de la clase Node.
     * @param archivoFXML Nombre del archivo .fxml al que se quiera navegar.
     * @param mensaje Conjunto de datos que quieran comunicarse de un controlador de escenario a otro.
     */
    default void navegar(Node componenteUI, String archivoFXML, HashMap<String, String> mensaje){
        try {
            //Configuramos el nuevo escenario
            FXMLLoader loader = new FXMLLoader(getClass().getResource(DIRECTORIO_DE_ESCENARIOS + archivoFXML));
            Parent nuevaEscena = loader.load();
            Comunicador controladorDeEscena = loader.getController();
            controladorDeEscena.recibirMensaje(mensaje);
            controladorDeEscena.inicializarComponentes();
            //Configuración del Stage
            Stage stageActual = (Stage) componenteUI.getScene().getWindow();
            String nombreVentana = archivoFXML.replace(".fxml", "").replace("_", " ");
            stageActual.setTitle( NOMBRE_SISTEMA + " - " + nombreVentana);
            //Cambio de escenario
            stageActual.setScene(new Scene(nuevaEscena, ANCHO_PREDEF, ALTO_PREDEF));
            stageActual.setResizable(false);
            stageActual.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Navegación sin mensaje */
    default void navegar(Node componenteUI, String archivoFXML){
        navegar(componenteUI,archivoFXML,new HashMap<>());
    }

    /** Navegación exclusiva para el método main() */
    default void navegarMain(Stage stageActual, String archivoFXML){
        try {
            Parent nuevaEscena = FXMLLoader.load(getClass().getResource("recursos/escenarios/" + archivoFXML));
            stageActual.setTitle(NOMBRE_SISTEMA);
            stageActual.setScene(new Scene(nuevaEscena, ANCHO_PREDEF, ALTO_PREDEF));
            stageActual.setResizable(false);
            stageActual.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Comunicación entre escenarios
// https://www.youtube.com/watch?v=3G8nTLujI5U