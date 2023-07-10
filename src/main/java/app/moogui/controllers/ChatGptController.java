package app.moogui.controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

import app.moogui.constants.ApiConstants;
import org.json.JSONArray;
import org.json.JSONObject;


public class ChatGptController {
	



	protected String prompt;
	private static final String api_key = ApiConstants.gpt_key;
	private static final String URL = "https://api.openai.com/v1/chat/completions";
	private static final String REQUEST_BODY = """
			{"model": "gpt-3.5-turbo",
			 "messages": [{"role": "user", "content": "%s"}],
			 "temperature": 0.7
			} 
			""";
	
	
	
	
	public ChatGptController(String prompt) {
		this.prompt = prompt;
	}



	public String getPrompt() {
		return prompt;
	}



	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}



	public String chat() throws Exception {
		
		String postBody = REQUEST_BODY
				.formatted(prompt);
		
		var request = HttpRequest.newBuilder()
				.uri(URI.create(URL))
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + api_key)
				.POST(BodyPublishers.ofString(postBody))
				.build();
		
		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
//		Assertions.assertEquals(200, response.statusCode());
		String resp = response.body().toString();
		JSONObject myResponse = new JSONObject(resp.toString());
		JSONArray choices = myResponse.getJSONArray("choices");
		JSONObject firstChoice = choices.getJSONObject(0);
		JSONObject messageObject = firstChoice.getJSONObject("message");
		String content = messageObject.getString("content");

		
		return content;
	}
}

