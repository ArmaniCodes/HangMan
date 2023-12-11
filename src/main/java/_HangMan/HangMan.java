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
	
	
	//Goes through the entire word and everytime it locates a letter it puts it in the hashMap and save its index in an arrayList.
	//So if a letter appears twice it will only be stored once but the arraylist will have two indexs.
	private void setUpHM() {
		for(int i = 0; i < length; ++i) {
			char c = word.charAt(i);
			ArrayList<Integer> ar = hp.getOrDefault(c, new ArrayList<Integer>());
			ar.add(i);
			
			hp.put(c,ar);
		}
		
	}
	
	
	HangMan(){
		this.word = GenerateWord.generateWord();
		this.word = word.replace("[", "").replace("]", "").replace("\"", "");// filter out "" and []
		this.length = word.length();
		setUpHM();
	}
	
	HangMan(int length){
		this.word = GenerateWord.generateWord(length);
		this.word = word.replace("[", "").replace("]", "").replace("\"", "");  // filter out "" and []
		this.length = length;
		setUpHM();
	}
	
	

	public int getWordLength() {
		return this.length;
	}
	
	public String getWord() {
		return word;
	}
	
	
}
