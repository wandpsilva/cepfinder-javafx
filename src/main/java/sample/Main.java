package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/View.fxml"));
            Scene scene = new Scene(parent);
            primaryStage.setTitle("CEPFINDER");
            primaryStage.setScene(scene);
            primaryStage.resizableProperty().setValue(Boolean.FALSE);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
