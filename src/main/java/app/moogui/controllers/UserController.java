package app.moogui.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.moogui.models.UserModel;
import app.moogui.services.UserService;


@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping(value="/users")
	public ResponseEntity<List<UserModel>> findAll(){
		List<UserModel> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/users/{id}")
	public ResponseEntity<UserModel> findById(@PathVariable long id){
		UserModel obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping(value="/users/{id}")
	public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UserModel obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<UserModel> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	@GetMapping(value = "/choices")
	public List<String> getTitles() {
//		long tempoInicial = System.currentTimeMillis();
		GptController gpt = new GptController();
		List<String> gptResponse = gpt.getResponse(service);
//		getJsonTitles(gptResponse);
//		return  System.currentTimeMillis() - tempoInicial;
		return getJsonTitles(gptResponse);

	}
	
	private List<String> getJsonTitles(List<String> gptResponse) {
		List<String> titles = new ArrayList<String>();
		TitleController t = new TitleController(gptResponse);
		for(int i=0;i<gptResponse.size();i++) {
			titles.add(t.getTitle(i));
		}


		
		return titles;
		
	}
	
	
}
