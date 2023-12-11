package _HangMan;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MyController {
	@FXML
	private ImageView myImageView;
	@FXML
	private Button startButton;
	@FXML
	private CheckBox myCheckBox;
	@FXML
	private TextField lengthBox;
	@FXML
	private AnchorPane myAnchor;
	@FXML
	private TextField submitField;
	@FXML
	private Button submitButton;

	private Text[] underScoreList;
	private int currentImageNum = 1;
	private String imageDirectory = "/_HangMan/HangManImages/";

	// Game of hangMan
	private HangMan hm;
	
	private String gameState;

	private void enableSubmits() {
		submitField.setDisable(false);
		submitField.setVisible(true);
		submitButton.setDisable(false);
		submitButton.setVisible(true);

	}

	private void disableSubmits() {
		submitField.setDisable(true);
		submitField.setVisible(false);
		submitButton.setDisable(true);
		submitButton.setVisible(false);

	}

	private void disableStartButton() {
		startButton.setDisable(true);
		startButton.setVisible(false);
	}

	private void enableStartButton() {
		startButton.setDisable(false);
		startButton.setVisible(true);
	}

	// Ensure user puts a numerical character in range
	private void inputValidation() {
		String length = lengthBox.getText();
		int lengthOfWord = -1;
		try {
			lengthOfWord = Integer.parseInt(length);
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid number.");
			return;
		}
		if (lengthOfWord <= 15 && lengthOfWord > 1) {
			hm = new HangMan(lengthOfWord);
		} else {
			System.out.println("Invalid Range. Range 2-15");
		}
	}

	// Formats each new underLine and sets their position to the previous underlines
	// position + 25
	private void formatUnderLine(Text punderline, Text underline) {
		underline.setFont(Font.font("monospace", 35.0));
		underline.setLayoutX((punderline).getLayoutX() + 25);
		underline.setLayoutY(345);
		this.myAnchor.getChildren().add(underline);
	}

	private void setUpWords() {
		this.underScoreList = new Text[this.hm.getWordLength()]; // Initialize underScore Array

		// Initialize First underline
		Text ogUnderLine = new Text("_");
		ogUnderLine.setFont(Font.font("monospace", 35.0));
		ogUnderLine.setLayoutX(14);
		ogUnderLine.setLayoutY(345);
		this.myAnchor.getChildren().add(ogUnderLine);
		underScoreList[0] = ogUnderLine;

		// Create other underLines
		for (int i = 1; i < this.hm.getWordLength(); ++i) {
			Text underscoreText = new Text("_");
			formatUnderLine(underScoreList[i - 1], underscoreText);
			this.underScoreList[i] = underscoreText;
		}
	}

	private void startGame() {
		// If inputLength checkbox Checked then we check if user input is Correct
		if (this.myCheckBox.isSelected()) {
			inputValidation();
			setUpWords();
		} else {
			hm = new HangMan();
			setUpWords();
		}

		// If hangman Game created then we can continue with game setup
		if (hm != null) {
			disableStartButton();
			enableSubmits();
			changeImage();
			gameState = "Started";
		}
	}
	
	
	private void endGame() {
		disableSubmits();
		//This means player won
		if (hm.getDiscoveredLetters() == hm.getWordLength()) {
			enableStartButton();
			startButton.setText("You won! Restart?");
			gameState = "Won";
			return;
		}
		else {
			enableStartButton();
			startButton.setText("You lost! Reveal word?");
			gameState = "Lost";
			return;
		}
	}
	
	private void checkIfGameFinished() {
		if (hm.getDiscoveredLetters() == hm.getWordLength()) {
			endGame();
		}	
	}
	
	
	//We check if game is finished before and after submitting so that the player doesn't need to click the button again to see if they won.
	private void submitAnswer() {
		checkIfGameFinished();
		String letter = submitField.getText();
		if (letter.length() == 1 && Character.isAlphabetic(letter.charAt(0))) { // Check if valid letter
			ArrayList<Integer> indexlist = hm.checkLetter(letter.charAt(0));
			char lttr = letter.charAt(0);
			if (indexlist == null) {
				changeImage(); // if returns null then we know letter doesn't exist in word so we can change
								// image of stickman!
			} else {

				if (!(hm.isDiscovered(lttr))) { // If returns false then letter hasn't been discovered yet
					for (int i : indexlist) {   // Loop through indexlist and discover letter as well as change underline to the letter
						hm.discoverLetter(lttr);
						underScoreList[i].setText(letter);
					}
					checkIfGameFinished();
				}

			}

		}

	}
	
	public void cleanUp() {
		for(Text i: underScoreList) {
			this.myAnchor.getChildren().remove(i);
		}
		underScoreList = null;
	}
	
	public void revealWord() {
		String word = hm.getWord();
		for (int i = 0; i < underScoreList.length; ++i) {
			underScoreList[i].setText("" + word.charAt(i));
		}
	}
	
	public void initialize() {
		startButton.setOnAction(event -> {
			if (gameState == null) { // if game state null then game hasn't started
				startGame();
			}
			if(gameState == "Won") {
				cleanUp();
				gameState = null;
				startButton.setText("Start");
			}
		
			if(gameState == "Lost") {
				
			}
			
		});

		submitButton.setOnAction(event -> {
			submitAnswer();
		});

	}

	private void changeImage() {
		//Game ended because user failed to guess 
		if (currentImageNum >= 7) {
			currentImageNum = 0;
			endGame();			
		}
		Image newImage = new Image(getClass().getResourceAsStream(imageDirectory + ++currentImageNum + ".png"));
		myImageView.setImage(newImage);
	}
}
