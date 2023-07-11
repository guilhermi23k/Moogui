package app.moogui.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.moogui.constants.ApiConstants;
import app.moogui.models.GptModel;


@RestController
public class TitleController {
	
	
	@GetMapping(value = "/choices")
	public List<String> getTitles() {
		return getGptResponse();
			
	}
	
	public List<String> movieApi(String[] GptResponse) {
		List<String> titles = null;
		for(int i=0; i<2; i++) {
			try {
				HttpRequest request = HttpRequest.newBuilder()
						.uri(URI.create("http://www.omdbapi.com/?apikey="+ ApiConstants.omdb_key +"t=" + GptResponse[i]))
						.header("accept", "application/json")
						.method("GET", HttpRequest.BodyPublishers.noBody())
						.build();
				HttpResponse<String> response;
				try {
					response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
					titles.add(response.body());
				} catch (IOException | InterruptedException e) {
					titles.add(e.getMessage());
				}
			}catch(Exception e) {
				titles.add(e.getMessage());
			}
		}
		
		return titles;
		
	}
	
	
	public List<String> getGptResponse() {
		List<String> titles = new ArrayList();
		List<String> gens = new ArrayList();
		gens.add("Ação");
		gens.add("Crime");
		titles.add("Enrolados, a princesa e o sapo");
		
		
		GptModel gpt = new GptModel(
				"Olá! Estou procurando algumas recomendações de filmes. "
						+ "Eu realmente gostei de assistir os filmes e séries " + titles
						+ ". Meus gêneros de filme favoritos são " + gens
						+ ". Você poderia me recomendar alguns filmes ou séries atuais com base nessas preferências?"
						+ "retorne a resposta APENAS como JSON Object em pt-br separado em filmes e series"
				);
		try {
			return formatGpt(gpt.chat());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	List<String> formatGpt(String gpt) {
		List<String> formatedGpt = new ArrayList();
		JSONObject gptObject = new JSONObject(gpt);
		JSONArray titles = gptObject.getJSONArray("filmes"); 
		JSONArray series = gptObject.getJSONArray("series"); 
		
		for(int i=0; i<titles.length(); i++) {
			JSONObject title = titles.getJSONObject(i);
			formatedGpt.add(title.getString("titulo"));
		}
		for(int i=0; i<series.length(); i++) {
			JSONObject serie = series.getJSONObject(i);
			formatedGpt.add(serie.getString("titulo"));
		}
		
		return formatedGpt;
	}
	
	
}
