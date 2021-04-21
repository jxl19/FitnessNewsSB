package com.fitnessnews;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitnessnews.models.PersonalInfo;
import com.fitnessnews.models.Users;
import com.fitnessnews.repository.PersonalInfoRepository;
import com.fitnessnews.repository.UsersRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class ForgotPasswordControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsersRepository uRepository;
    
    @Autowired
    private PersonalInfoRepository pRepository;
    
    
    @Test
    void tokenWorks() throws Exception{
    	
    	PersonalInfo pi = new PersonalInfo(900, "Iam", "Test", false);
    	Users user = new Users(900, "anew2@email.com", "secure", false, null, pi);
    	
    	mvc.perform(post("/api/registration").contentType("application/json").content(objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    	mvc.perform(post("/user/create").contentType("application/json").content(objectMapper.writeValueAsString(pi))).andExpect(status().isOk());
    	
    	Users testu = uRepository.findByEmail("anew2@email.com");
    	
    	assertEquals(null, testu.getResetPasswordToken());    
    	mvc.perform(post("/reset/forgotpass").contentType("application/json").content("anew2@email.com")).andExpect(status().isOk());

    	Users testus = uRepository.findByEmail("anew2@email.com");


    	assertNotNull(testus.getResetPasswordToken());
    }
    
    @Test
    void resetWorks() throws Exception{
    	
    	PersonalInfo pi = new PersonalInfo(900, "Iam", "Test", false);
    	Users user = new Users(900, "anew1@email.com", "secure", false, "asdfghjkl;'", pi);
    	
    	mvc.perform(post("/api/registration").contentType("application/json").content(objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    	mvc.perform(post("/user/create").contentType("application/json").content(objectMapper.writeValueAsString(pi))).andExpect(status().isOk());
    	
    	String query = "{\"token\":\"asdfghjkl;'\", \"password\":\"changed\"}";
    	mvc.perform(post("/reset/resetpass").contentType("application/json").content(query)).andExpect(status().isOk());

    	Users testu = uRepository.findByEmail("anew1@email.com");
    	assertEquals(null, testu.getResetPasswordToken());
    	assertEquals("changed", testu.getPassword());
    }
}