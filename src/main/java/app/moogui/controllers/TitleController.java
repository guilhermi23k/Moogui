package app.moogui.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.moogui.constants.ApiConstants;


@RestController
public class TitleController {
	
	
	@GetMapping(value = "/choices")
	public String getTitles() {
		return getGptResponse();		
	}
	
	private String movieapi() {
		String title = null;
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("http://www.omdbapi.com/?apikey="+ ApiConstants.omdb_key +"t=friends"))
			    .header("accept", "application/json")
			    .method("GET", HttpRequest.BodyPublishers.noBody())
			    .build();
			HttpResponse<String> response;
			try {
				response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
				return response.body();
			} catch (IOException | InterruptedException e) {
				return e.getMessage();
			}
		
	}
	
	
	private String getGptResponse() {
		List<String> titles = new ArrayList();
		List<String> gens = new ArrayList();
		gens.add("Acao");
		gens.add("Crime");
		titles.add("Enrolados");
		
		
		ChatGptController gpt = new ChatGptController(
				"Olá! Estou procurando algumas recomendações de filmes. "
						+ "Eu realmente gostei de assistir os filmes e séries "
						 + titles
						+ ". Meus gêneros favoritos são " + gens
						+ ". Você poderia me recomendar alguns filmes ou séries atuais com base nessas preferências?"
						+ "formate a resposta na mesma linha apenas com os nomes dos filmes ou séries, sem fazer distinção,"
						+ " usando ; como separador"
				);
		try {
			return gpt.chat();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
}
