package _HangMan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class HangMan {
	private int length;
	private String word;
	private HashMap<Character,ArrayList<Integer>> hp = new HashMap<>();
	private HashSet<Character> hs = new HashSet<>();
	private int discoveredLetters = 0;
	
	HangMan(){
		this.word = GenerateWord.generateWord();
		this.word = word.replace("[", "").replace("]", "").replace("\"", "");// filter out "" and []
		this.length = word.length();	
	}
	
	HangMan(int length){
		this.word = GenerateWord.generateWord(length);
		this.word = word.replace("[", "").replace("]", "").replace("\"", "");  // filter out "" and []
		this.length = length;
	}
	
	

	public int getWordLength() {
		return this.length;
	}
	
	public String getWord() {
		return word;
	}
	
	
}
