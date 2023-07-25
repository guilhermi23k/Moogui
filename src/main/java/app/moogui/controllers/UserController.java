package app.moogui.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.moogui.models.Gender;
import app.moogui.models.Title;
import app.moogui.models.UserModel;
import app.moogui.services.GenderService;
import app.moogui.services.TitleService;
import app.moogui.services.UserService;


@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private TitleService serviceT;
	
	@Autowired
	private GenderService serviceG;
	
	@GetMapping(value="/users")
	public ResponseEntity<List<UserModel>> findAll(){
		List<UserModel> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/user/{id}")
	public ResponseEntity<UserModel> findById(@PathVariable long id){
		UserModel obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping(value="/user/{id}")
	public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UserModel obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/user/{id}")
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
		TitleController t = new TitleController(gptResponse, serviceT);
		for(int i=0;i<gptResponse.size();i++) {
			titles.add(t.getTitle(i));
		}
		return titles;
	}
	
	
	@PostMapping(value = "/favoritedTitle")
	public ResponseEntity<String> FavoritedTitle(@RequestBody String titleId, @RequestBody List<Long> gendersId) {
		ResponseEntity response = null;
		
		try {
			Title t = serviceT.findById(titleId);
			UserModel u =  service.findByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			u.addFavTitle(t);
			gendersId.forEach(g -> {
				Gender gen = serviceG.findById(g);
			    u.addFavGender(gen);
			});
			response = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Title added");
		}catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + ex.getMessage());
        }
        return response;
        
    }
	   
	
	
	
	
}
