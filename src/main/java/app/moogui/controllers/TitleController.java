package app.moogui.controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import app.moogui.constants.ApiConstants;
import app.moogui.services.UserService;


public class TitleController implements Runnable{
	private List<String> titles = new ArrayList();
	private String title;
	
	public TitleController(String title) {
		this.title = title;
	}
	
	
	@Autowired
	private UserService serv;
	
	
	
//	public List<String> getGptResponse() {
//		GptController response = new GptController();
//		return response.getResponse();
//	}
	
	
	private String setTitle(String title) {
		try {
			HttpRequest request = HttpRequest.newBuilder()
				    .uri(URI.create("https://api.themoviedb.org/3/find/" + title +"?external_source=imdb_id&language=pt-br"))
				    .header("accept", "application/json")
				    .header("Authorization", "Bearer " + ApiConstants.tmdb_key)
				    .method("GET", HttpRequest.BodyPublishers.noBody())
				    .build();
				HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
				return response.body();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getTitles(){
		return titles;
	}
	

	@Override
	public void run() {
		titles.add(setTitle(this.title));
	}
	
	
	
}
