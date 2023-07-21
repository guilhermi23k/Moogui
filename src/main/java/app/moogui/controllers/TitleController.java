package app.moogui.controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;

import app.moogui.constants.ApiConstants;
import app.moogui.services.UserService;


public class TitleController {
	private List<String> titles = new ArrayList();
	
	public TitleController(List<String> titles) {
		this.titles = titles;
	}
	
	
	private String getTitleDetails(String titleID) {
		try {
			HttpRequest request = HttpRequest.newBuilder()
				    .uri(URI.create("https://api.themoviedb.org/3/find/" + titleID +"?external_source=imdb_id&language=pt-br"))
				    .header("accept", "application/json")
				    .header("Authorization", "Bearer " + ApiConstants.tmdb_key)
				    .method("GET", HttpRequest.BodyPublishers.noBody())
				    .build();
				HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
				String aux = response.body();
				return aux;
		}catch(Exception e) {
			e.printStackTrace();
			return e.getMessage().toString();
		}
	}
	
	public String getTitle(int i){
		return getTitleDetails(getSyncTitle(i));
	}
	private synchronized String getSyncTitle(int flag) {
		return titles.get(flag);
	}
	
	
	
	
}
