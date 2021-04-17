package com.fitnessnews.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessnews.models.Users;
import com.fitnessnews.repository.UsersRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UsersRepository usersRepository; 
	
    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
}
