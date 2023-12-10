package _HangMan;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class GenerateWord {
	
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
	
	
	
}
