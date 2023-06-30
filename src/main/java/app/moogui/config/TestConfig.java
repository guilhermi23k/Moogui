package app.moogui.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import app.moogui.models.UserModel;
import app.moogui.services.UserService;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	@Autowired
	private UserService service;

	@Override
	public void run(String... args) throws Exception {
		UserModel u1 = new UserModel(null, "mariabb", "maria@gmail.com", "123456", "free_user");
		UserModel u2 = new UserModel(null, "Alexgg", "alex@gmail.com", "123456", "free_user");
		
		service.newUser(u1);
		service.newUser(u2);
	}
	
	
}
