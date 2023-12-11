package _HangMan;

public class GenerateHint extends GenerateWord {

	private static String URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";
	
	public static String parseDefinition(String json) {
		
		   int defIndex = json.indexOf("\"definition\":\"");
		   if (defIndex != -1){
			   defIndex += "\"definition\":\"".length();
			   int defEndIndex = json.indexOf("\"", defIndex);
			   
			   if (defEndIndex != -1) {
	                // Extract the definition
	                String definition = json.substring(defIndex, defEndIndex);

	               return definition;
	            }
		   }
		return null;
	}
	
	
	public static String generateDefinition(String word)  {
		String response = "";
		try {
		response = sendGetRequest(URL+word);
		}
		catch (Exception e) {
	        e.printStackTrace();
	    }
		
		return parseDefinition(response);
	}
	
}
