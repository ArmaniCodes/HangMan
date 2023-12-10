package _HangMan;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
@Override
public void start(Stage primaryStage) {
	try {
		AnchorPane root = FXMLLoader.load(getClass().getResource("/_HangMan/HangMan.fxml"));
	
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Hang Man");
		primaryStage.show();
		primaryStage.setResizable(false);
	} catch(Exception e) {
		e.printStackTrace();
	}
}

public static void main(String[] args) {

	launch(args);
	
}







}
