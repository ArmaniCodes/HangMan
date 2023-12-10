package _HangMan;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MyController {
	@FXML
	private ImageView myImageView;
	
	private int currentImageNum = 0;
	private String imageDirectory = "/HangManImages/";
	
	
	
	private void changeImage() {
		if (currentImageNum >= 7) {
			currentImageNum = 0;
		}
		Image newImage = new Image(getClass().getResourceAsStream(imageDirectory + ++currentImageNum + ".png" ));
		myImageView.setImage(newImage);
	}
}
