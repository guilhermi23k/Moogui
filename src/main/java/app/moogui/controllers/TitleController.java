package app.moogui.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.moogui.constants.ApiConstants;
import app.moogui.models.GptModel;
import app.moogui.models.Title;
import app.moogui.models.UserModel;
import app.moogui.services.UserService;


@RestController
public class TitleController {
	
	private UserService serv;
	
	@GetMapping(value = "/choices")
	public List<String> getTitles() {
		return movieApi(getGptResponse());
			
	}
	
	public List<String> movieApi(List<String> GptResponse) {
		List<String> titles = new ArrayList();
		for(int i=0; i<GptResponse.size(); i++) {
			try {
				HttpRequest request = HttpRequest.newBuilder()
					    .uri(URI.create("https://api.themoviedb.org/3/find/" + GptResponse.get(i) +"?external_source=imdb_id&language=pt-br"))
					    .header("accept", "application/json")
					    .header("Authorization", "Bearer " + ApiConstants.tmdb_key)
					    .method("GET", HttpRequest.BodyPublishers.noBody())
					    .build();
					HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
				try {
					response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
					titles.add(response.body());
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return titles;
		
	}
	
	
	public List<String> getGptResponse() {
		UserService service = new UserService();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserModel obj = service.findByEmail(auth.getName().toString());	
		
		GptModel gpt = new GptModel(
				"Olá! Estou procurando algumas recomendações de filmes. "
						+ "Eu realmente gostei de assistir os filmes e séries " + 
						obj.getFavTitles().stream().map(t -> t.getName()).collect(Collectors.toList())
						+ ". Meus gêneros de filme favoritos são " + 
						obj.getFavGenders().stream().map(t -> t.getType()).collect(Collectors.toList())
						+ ". Você poderia me recomendar alguns filmes ou séries atuais com base nessas preferências?"
						+ "retorne a resposta APENAS como JSON Object em pt-br separado em filmes e series, "
						+ "listando o imdbID de cada"
				);
		try {
			return formatGpt(gpt.chat());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public List<String> formatGpt(String gpt) {
		List<String> formatedGpt = new ArrayList();
		JSONObject gptObject = new JSONObject(gpt);
		JSONArray titles = gptObject.getJSONArray("filmes"); 
		JSONArray series = gptObject.getJSONArray("series"); 
		
		for(int i=0; i<titles.length(); i++) {
			JSONObject title = titles.getJSONObject(i);
			formatedGpt.add(title.getString("imdbID"));
		}
		for(int i=0; i<series.length(); i++) {
			JSONObject serie = series.getJSONObject(i);
			formatedGpt.add(serie.getString("imdbID"));
		}
		
		return formatedGpt;
	}
	
	
	
}
