package sla;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TextInput extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
        Parent root = loader.load();

        Controller myController = loader.getController();
        primaryStage.setOnHidden(e -> myController.save());

        primaryStage.setTitle("Text Input");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
