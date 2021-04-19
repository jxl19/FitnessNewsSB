package com.fitnessnews.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import java.util.Optional.*;

import com.fitnessnews.exceptions.ResourceNotFoundException;
import com.fitnessnews.models.Users;
import com.fitnessnews.repository.UsersRepository;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UsersRepository usersRepository;

	@GetMapping("/users")
	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}
	
	@GetMapping("/users/{email}")
	public ResponseEntity<Users> getEmployeeByEmail(@PathVariable(value = "email") String email) throws ResourceNotFoundException{
		Users user = usersRepository.findByEmail(email);
        return ResponseEntity.ok().body(user);
	}
	
	@PutMapping("/updateinfo")
	public ResponseEntity<Users> updateUsers(@PathVariable(value = "id") Long userID,
			@Valid @RequestBody Users userDetails) throws ResourceNotFoundException {
		final Users user = usersRepository.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + userID));
		user.setPassword(userDetails.getPassword());
		final Users updatedUser = usersRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	@PostMapping("/registration")
    public Users createUser(@Valid @RequestBody Users user) {
        return usersRepository.save(user);
    }
	
}
