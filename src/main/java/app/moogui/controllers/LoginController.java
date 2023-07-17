package app.moogui.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.moogui.models.UserModel;
import app.moogui.services.UserService;

@RestController
public class LoginController {
//
//    @Autowired
//    private UserRepository repo;
    
    @Autowired
    private UserService serv;
    
    
    @Autowired
    private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserModel user) {
    	UserModel savedUser = null;
        ResponseEntity response = null;
        try {
            String hashPwd = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashPwd);
            savedUser = serv.create(user);
            if (savedUser.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + ex.getMessage());
        }
        return response;
        
    }
	
	 @RequestMapping("/user")
	    public UserModel getUserDetailsAfterLogin(Authentication authentication) {
		 try {
			 Optional<UserModel> userO = Optional.of(serv.findByEmail(authentication.getName()));
			 List<UserModel> user = userO.map(Collections::singletonList)
					 .orElse(Collections.emptyList());
			 if (user.size() > 0) {
				 return user.get(0);
			 } else {
				 return null;
			 }
			 
		 }catch (Exception e) {
			 e.printStackTrace();
			 return null;
		 }

	    }

}
