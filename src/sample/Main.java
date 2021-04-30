package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import utilitaires.LoadView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        LoadView.showView("Formulaire De Location", "LocationJava.fxml",1);
    }


    public static void main(String[] args) {

        launch(args);
    }
}
