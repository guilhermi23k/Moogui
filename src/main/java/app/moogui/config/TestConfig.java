package app.moogui.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import app.moogui.models.UserModel;
import app.moogui.services.UserService;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	@Autowired
	private UserService service;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		UserModel u = new UserModel(null, "teste", "teste@gg.com", "123456", "free_user");
		String hashPwd = passwordEncoder.encode(u.getPassword());
    	u.setPassword(hashPwd);
		service.create(u);
	}
	
	
}
