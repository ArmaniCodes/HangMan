package _HangMan;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MyController {
	@FXML
	private ImageView myImageView;
	@FXML
	private Button startButton;
	@FXML
	private CheckBox myCheckBox;
	@FXML
	private TextField lengthBox;
	

	private int currentImageNum = 0;
	private String imageDirectory = "/_HangMan/HangManImages/";
	
	//Game of hangMan
	private HangMan hm;
	
	// Ensure user puts a numerical character in range
	private void inputValidation() {
		String length = lengthBox.getText();
		int lengthOfWord = -1;
		try { 
			lengthOfWord = Integer.parseInt(length);
		}catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid number.");
			return;
		}
		if (  lengthOfWord <= 15 && lengthOfWord > 1) {
			hm = new HangMan(lengthOfWord);
		}
		else {
			System.out.println("Invalid Range. Range 2-15");
		}
		
	}
	
	

	private void startGame() {
		//Check if correct Number is inputted
		if (this.myCheckBox.isSelected()) {
			inputValidation();
		}
		else {
			hm = new HangMan();
			
		}
		
	}
	
	
	
	public void initialize() {
		startButton.setOnAction(event -> {
			startGame();
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
