package app.moogui.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;

import app.moogui.models.GptModel;
import app.moogui.models.UserModel;
import app.moogui.services.UserService;

public class GptController {
	
	
	
	public List<String> getResponse(UserService userService){
		Object username = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserModel user = userService.findByEmail(username.toString());
		List<String> titles = user.getFavTitlesName();
		List<String> genders = user.getFavGendersName();
        
		GptModel gpt = new GptModel(
				"Olá! Estou procurando algumas recomendações de filmes. "
						+ "Eu realmente gostei de assistir os filmes e séries " + 
						"John Wick" +
						titles
						+ ". Meus gêneros de filme favoritos são " + 
						"Ação" + 
						genders
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
