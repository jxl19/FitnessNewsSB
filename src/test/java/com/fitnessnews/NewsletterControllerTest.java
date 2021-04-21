package com.fitnessnews;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import com.fitnessnews.controllers.NewsletterController;
import com.fitnessnews.models.NewsletterSet;
import com.fitnessnews.models.Newsletters;
import com.fitnessnews.models.PersonalInfo;
import com.fitnessnews.models.Users;
import com.fitnessnews.repository.NewsletterRepository;
import com.fitnessnews.repository.PersonalInfoRepository;
import com.fitnessnews.repository.UsersRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class NewsletterControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NewsletterRepository nRepository;
    
    @Autowired
    private NewsletterController nlController;
    
    @Test
    void registrationWorks() throws Exception{
    	Date date = new Date();
    	Newsletters nl = new Newsletters(900L, date, "Test", "Iam",
    			"Atest", "This is a test", "Still a test");
    
    	mvc.perform(post("/newsletters/createnews").contentType("application/json").content(objectMapper.writeValueAsString(nl))).andExpect(status().isOk());

    	NewsletterSet testn = nRepository.getLatestNewsletter();
    	assertEquals("Atest", testn.getAuthLastName());
    	assertEquals("Test", testn.getHeader());
    }
    
    @Test
    void getAllWorks() throws Exception{
    	
    	List<Newsletters> test = nlController.getAllNewsletters();
    	
    	assertNotNull(test);
    }
}
