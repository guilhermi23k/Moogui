package app.moogui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import app.moogui.models.UserModel;
import app.moogui.repositories.UserRepository;

public class LoginController {

    @Autowired
    private UserRepository repo;

	@PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserModel customer) {
    	UserModel savedCustomer = null;
        ResponseEntity response = null;
        try {
            savedCustomer = repo.save(customer);
            if (savedCustomer.getId() > 0) {
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

}
