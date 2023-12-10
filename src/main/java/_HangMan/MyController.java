package _HangMan;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MyController {
	@FXML
	private ImageView myImageView;
	@FXML
	private Button startButton;
	
	private int currentImageNum = 0;
	private String imageDirectory = "/_HangMan/HangManImages/";
	

	public void initialize() {
		startButton.setOnAction(event -> {
			changeImage();
			
		}
);
		
	
	}
	
	private void changeImage() {
		if (currentImageNum >= 7) {
			currentImageNum = 0;
		}
		Image newImage = new Image(getClass().getResourceAsStream(imageDirectory + ++currentImageNum + ".png" ));
		myImageView.setImage(newImage);
	}
}
