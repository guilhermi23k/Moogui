package app.moogui.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TitleController {
	
	@GetMapping(value = "/titles")
	public String getTitles() {
		return "filminhos";
		
	}

}
