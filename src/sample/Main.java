package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.controlador.arquitectura.Navegador;

public class Main extends Application implements Navegador {

    @Override
    public void start(Stage primaryStage) {
        navegarMain(primaryStage, "Login.fxml");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
