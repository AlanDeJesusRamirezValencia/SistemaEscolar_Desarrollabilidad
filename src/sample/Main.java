package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.ui.Navegador;

public class Main extends Application implements Navegador {

    @Override
    public void start(Stage primaryStage) throws Exception{
        navegarMain(primaryStage, "Login.fxml");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
