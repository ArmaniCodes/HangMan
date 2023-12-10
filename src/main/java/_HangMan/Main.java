package _HangMan;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
       
        Label helloLabel = new Label("Test");

   
        StackPane root = new StackPane();
        root.getChildren().add(helloLabel); 

       
        Scene scene = new Scene(root, 300, 250); 

   
        primaryStage.setTitle("JavaFX Example");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    public static void main(String[] args) {
        launch(args);
    }
}