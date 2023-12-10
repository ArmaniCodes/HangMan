package _HangMan;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class GenerateWord {
	
	//Method to sendGetRequest to a URL
	private static String sendGetRequest(String urlStr) throws Exception {
	    StringBuilder result = new StringBuilder();
	    URL url = new URL(urlStr);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
	        for (String line; (line = reader.readLine()) != null; ) {
	            result.append(line);
	        }
	    }
	    return result.toString();
	}
	
	
	
	//This is the same method as generateWord() but overloaded to receive an integer Length
	//This length variable allows us to specify how long we want the rando generated word to be
	public static String generateWord(int length) {
		String response = null;
		String apiURL = "https://random-word-api.herokuapp.com/word?length=";
		try {
		        String sendURL = apiURL + length; 
		        response = sendGetRequest(sendURL);
		       
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		
		return response;
	}
	
	public static String generateWord() {
		String response = null;
		try {
		        String apiUrl = "https://random-word-api.herokuapp.com/word"; 
		        response = sendGetRequest(apiUrl);
		       
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		
		return response;
	}
	
	
	
}
