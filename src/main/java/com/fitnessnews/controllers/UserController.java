package com.fitnessnews.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessnews.exceptions.ResourceNotFoundException;
import com.fitnessnews.models.Users;
import com.fitnessnews.repository.UsersRepository;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UsersRepository usersRepository;

	@GetMapping("/users")
	public List<Users> getAllUsers() {
		LOGGER.info("A list of all users were displayed");
		return usersRepository.findAll();
	}
	
	@GetMapping("/users/{email}")
	public ResponseEntity<Users> getEmployeeByEmail(@PathVariable(value = "email") String email) throws ResourceNotFoundException{
		Users user = usersRepository.findByEmail(email);
        LOGGER.info("User "+email+ " has logged in");
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping("/updatepassword/{id}")
	public ResponseEntity<Users> updateUsers(@PathVariable(value = "id") Integer userID,
			@Valid @RequestBody Users userDetails) throws ResourceNotFoundException {
		final Users user = usersRepository.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + userID));
		user.setPassword(userDetails.getPassword());
		final Users updatedUser = usersRepository.save(user);
		LOGGER.info("User "+userID+" requested a password change");
		return ResponseEntity.ok(updatedUser);
	}
	
	@PostMapping("/registration")
    public Users createUser(@Valid @RequestBody Users user) {
        LOGGER.info("A new user has been registered");
		return usersRepository.save(user);
    }
	
}
