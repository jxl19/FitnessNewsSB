package com.fitnessnews.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessnews.exceptions.ResourceNotFoundException;
import com.fitnessnews.models.Newsletters;
import com.fitnessnews.repository.NewsletterRepository;

@RestController
@RequestMapping("/newsletters")
public class NewsletterController {
	
	@Autowired
	private NewsletterRepository newsletterRepository;

	@GetMapping("/allnews")
	public List<Newsletters> getAllNewsletters() {
        return newsletterRepository.findAll();
    }
	
	@PostMapping("/createnews")
    public Newsletters createNewsletter(@Valid @RequestBody Newsletters newsletter) {
        return newsletterRepository.save(newsletter);
    }
	
	@GetMapping("/get/{id}")
    public ResponseEntity<Newsletters> getNewsletterById(@PathVariable(value = "id") Long newsletterID)
    throws ResourceNotFoundException {
    Newsletters newsletter = newsletterRepository.findById(newsletterID)
      .orElseThrow(() -> new ResourceNotFoundException("Newsletter not found for this id :: " + newsletterID));
    return ResponseEntity.ok().body(newsletter);
    }
	
	@PutMapping("/updatenews/{id}")
    public ResponseEntity<Newsletters> updateNewsletter(@PathVariable(value = "id") Long newsletterID,
        @Valid @RequestBody Newsletters newsletterDetails) throws ResourceNotFoundException {
        Newsletters newsletter = newsletterRepository.findById(newsletterID)
       .orElseThrow(() -> new ResourceNotFoundException("Newsletter not found for this id :: " + newsletterID));

        newsletter.setDatePublished(newsletterDetails.getDatePublished());
        newsletter.setHeader(newsletterDetails.getHeader());
        newsletter.setAuthFirstName(newsletterDetails.getAuthFirstName());
        newsletter.setAuthLastName(newsletterDetails.getAuthLastName());
        newsletter.setContent(newsletterDetails.getContent());
        newsletter.setFooter(newsletterDetails.getFooter());

        final Newsletters updatedNewsletter = newsletterRepository.save(newsletter);
        return ResponseEntity.ok(updatedNewsletter);
    }

}
