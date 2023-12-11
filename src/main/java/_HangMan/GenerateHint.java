package _HangMan;

public class GenerateHint extends GenerateWord {

	private static String URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";
	
	public static String generateDefinition(String word) throws Exception {
		
		String response = sendGetRequest(URL+word);
		
		
		
		return response;
	}
	
}
