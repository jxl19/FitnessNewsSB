package com.fitnessnews;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
public class PersonalInfoControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsersRepository uRepository;
    
    @Autowired
    private PersonalInfoRepository pRepository;
    
    @Test
    void registrationWorks() throws Exception{
    	
    	PersonalInfo pi = new PersonalInfo(900, "Iam", "Test", false);
    	Users user = new Users(900, "anew@email.com", "secure", false, "", pi);
    
    	mvc.perform(post("/api/registration").contentType("application/json").content(objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    	mvc.perform(post("/user/create").contentType("application/json").content(objectMapper.writeValueAsString(pi))).andExpect(status().isOk());

    	PersonalInfo testp = pRepository.findByfName("Iam");
    	assertEquals("Test", testp.getlName());
    	assertEquals(false, testp.getWantsMail());
    }
    
    @Test
    void updateWorks() throws Exception{
    	
    	PersonalInfo pi = new PersonalInfo(900, "Iam", "Test", false);
    	Users user = new Users(900, "anew@email.com", "secure", false, "", pi);
    
    	mvc.perform(post("/api/registration").contentType("application/json").content(objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    	mvc.perform(post("/user/create").contentType("application/json").content(objectMapper.writeValueAsString(pi))).andExpect(status().isOk());
    	
    	PersonalInfo updated = new PersonalInfo(900, "Update", "Iam", true);
    	mvc.perform(put("/user/update/{id}", 900).contentType("application/json").content(objectMapper.writeValueAsString(updated))).andExpect(status().isOk());


    	PersonalInfo testp = pRepository.findByfName("Update");
    	assertEquals("Iam", testp.getlName());
    	assertEquals(true, testp.getWantsMail());
    }
}
