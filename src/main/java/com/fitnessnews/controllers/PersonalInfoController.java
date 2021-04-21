package com.fitnessnews.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessnews.exceptions.ResourceNotFoundException;
import com.fitnessnews.models.PersonalInfo;
import com.fitnessnews.repository.PersonalInfoRepository;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class PersonalInfoController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonalInfoController.class);

	@Autowired
	private PersonalInfoRepository personalInfoRepository;
	
	@PostMapping("/create")
    public PersonalInfo createUser(@Valid @RequestBody PersonalInfo personalInfo) {
        LOGGER.info("A new user has been registered and their personal information was added to the database");
		return personalInfoRepository.save(personalInfo);
    }
	
	@PutMapping("/update/{id}")
	public ResponseEntity<PersonalInfo> updateUser(@PathVariable(value = "id") Integer userID, @Valid @RequestBody PersonalInfo personalInfo) throws ResourceNotFoundException {
		final PersonalInfo pInfo = personalInfoRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("Info not found for this id : " + userID));
		pInfo.setfName(personalInfo.getfName());
		pInfo.setlName(personalInfo.getlName());
		pInfo.setWantsMail(personalInfo.getWantsMail());
		final PersonalInfo updatedPInfo = personalInfoRepository.save(pInfo);
		LOGGER.info("User "+userID+"updated their personal information");
		return ResponseEntity.ok(updatedPInfo);
	}
}
