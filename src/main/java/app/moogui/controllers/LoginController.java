package app.moogui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import app.moogui.models.UserModel;
import app.moogui.repositories.UserRepository;

public class LoginController {

    @Autowired
    private UserRepository repo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserModel user) {
    	UserModel savedCustomer = null;
        ResponseEntity response = null;
        	String hashPwd = passwordEncoder.encode(user.getPassword());
        	user.setPassword(hashPwd);
            savedCustomer = repo.save(user);
        return response;
    }

}
