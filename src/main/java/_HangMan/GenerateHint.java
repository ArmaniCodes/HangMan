package _HangMan;
import org.json.JSONArray;
import org.json.JSONObject;

public class GenerateHint extends GenerateWord {

	private static String URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";	//Dictionary API we are using
	
	public static String parseDefinition(String json) {
		String definition = "Sorry! No Hint";

		JSONArray jsonArr = new JSONArray(json);
		JSONObject firstObj  = jsonArr.getJSONObject(0);
		
		if (firstObj.has("meanings")) {	//Check if the keyphrase meanings exist in the JSON response if it does then we may have a valid definition!
		
			JSONArray meaningsArray = firstObj.getJSONArray("meanings");
		if (meaningsArray.length() > 0) {
			JSONObject firstMeaning = meaningsArray.getJSONObject(0);
			JSONArray definitionsArray = firstMeaning.getJSONArray("definitions");
			  if (definitionsArray.length() > 0) {
				  JSONObject firstDefinition = definitionsArray.getJSONObject(0);
				   definition = firstDefinition.getString("definition");	//Return definition of the word from the JSON response 
			  }
		}
		}
		return definition;	//If for some reason definition wasn't overridden then "Sorry! No Hint" is returned by default.
	}
	
	
	public static String generateDefinition(String word)  {
		String response = "";
		try {
		response = sendGetRequest(URL+word);
		}
		catch (Exception e) {
			return "Sorry! No Hint"; // Means definition doesn't exist for the word in the API or something else went wrong!
	    }
		
		return parseDefinition(response);
	}
	
}
