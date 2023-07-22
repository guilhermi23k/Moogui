package app.moogui.controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import app.moogui.constants.ApiConstants;
import app.moogui.models.Title;
import app.moogui.services.TitleService;

@RestController
public class TitleController {
    private TitleService serv;
	
	private List<String> titles = new ArrayList();
	
	public TitleController() {
	}
	
	public TitleController(List<String> titles) {
		super();
		this.titles = titles;
	}
	
	
	public TitleController(List<String> titles,  TitleService serv) {
		super();
		this.titles = titles;
		this.serv = serv;
	}
	
	private String getTitleDetails(String titleId) {
		try {
			Title t =serv.findById(titleId);
			return t.getJson();
		}catch(NoSuchElementException ex) {
			try {
				HttpRequest request = HttpRequest.newBuilder()
						.uri(URI.create("https://api.themoviedb.org/3/find/" + titleId +"?external_source=imdb_id&language=pt-br"))
						.header("accept", "application/json")
						.header("Authorization", "Bearer " + ApiConstants.tmdb_key)
						.method("GET", HttpRequest.BodyPublishers.noBody())
						.build();
				HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
				String json = response.body();
				serv.create(setTitleByJson(titleId, json));
				return json;
			}catch(Exception e) {
				e.printStackTrace();
				return e.getMessage().toString();
			}
			
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
	
	
	private Title  setTitleByJson(String id, String json) {
		Title t = new Title(id, json);
		return t;
	}
	
	
}
