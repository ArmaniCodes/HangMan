package _HangMan;
import org.json.JSONArray;
import org.json.JSONObject;

public class GenerateHint extends GenerateWord {

	private static String URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";
	
	public static String parseDefinition(String json) {
		JSONArray jsonArray = new JSONArray(json);
		JSONObject firstObj = jsonArray.getJSONObject(0);
		JSONArray meaningsArray = firstObj.getJSONArray("meanings");
		
		if (meaningsArray.length() > 0) {
			JSONObject firstMeaning = meaningsArray.getJSONObject(0);
			JSONArray definitionsArray = firstMeaning.getJSONArray("definitions");
			  if (definitionsArray.length() > 0) {
				  JSONObject firstDefinition = definitionsArray.getJSONObject(0);
				  String definition = firstDefinition.getString("definition");
				  return definition;
			  }
		}
		
		return "Sorry! No Hint";
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
