package com.fitnessnews.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessnews.exceptions.ResourceNotFoundException;
import com.fitnessnews.models.NewsletterSet;
import com.fitnessnews.models.Newsletters;
import com.fitnessnews.models.PersonalInfo;
import com.fitnessnews.models.ResultSet;
import com.fitnessnews.models.Users;
import com.fitnessnews.repository.NewsletterRepository;
import com.fitnessnews.repository.PersonalInfoRepository;
import com.fitnessnews.repository.UsersRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/newsletters")
public class NewsletterController {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private NewsletterRepository newsletterRepository;

	@Autowired
	private UsersRepository usersRepository;

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
		Newsletters newsletter = newsletterRepository.findById(newsletterID).orElseThrow(
				() -> new ResourceNotFoundException("Newsletter not found for this id :: " + newsletterID));
		return ResponseEntity.ok().body(newsletter);
	}

	@PutMapping("/updatenews/{id}")
	public ResponseEntity<Newsletters> updateNewsletter(@PathVariable(value = "id") Long newsletterID,
			@Valid @RequestBody Newsletters newsletterDetails) throws ResourceNotFoundException {
		Newsletters newsletter = newsletterRepository.findById(newsletterID).orElseThrow(
				() -> new ResourceNotFoundException("Newsletter not found for this id :: " + newsletterID));

		newsletter.setDatePublished(newsletterDetails.getDatePublished());
		newsletter.setHeader(newsletterDetails.getHeader());
		newsletter.setAuthFirstName(newsletterDetails.getAuthFirstName());
		newsletter.setAuthLastName(newsletterDetails.getAuthLastName());
		newsletter.setContent(newsletterDetails.getContent());
		newsletter.setFooter(newsletterDetails.getFooter());

		final Newsletters updatedNewsletter = newsletterRepository.save(newsletter);
		return ResponseEntity.ok(updatedNewsletter);
	}

	@GetMapping("/sendtosubscribers")
	public void sendNewsletter() throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		NewsletterSet newsletter = newsletterRepository.getLatestNewsletter();
		
		System.out.println(newsletter.getNewsletterID());
		
		
		List<ResultSet> rs = usersRepository.getListOfSubscribers();
		

		for (int i = 0; i < rs.size(); i++) {
			String recipientEmail = rs.get(i).getEmail();
			helper.setFrom("testserverrev@gmail.com", "FitnessNews");
			helper.setTo(recipientEmail);

			String subject = newsletter.getHeader();

			String content = "<h3>" + newsletter.getHeader() + "</h3><br><p>By: " + newsletter.getAuthFirstName() + " "
					+ newsletter.getAuthLastName() + "</p><br><p>" + newsletter.getDatePublished() + "</p><br><br><p>"
					+ newsletter.getContent() + "</p><br><br><br><p>" + newsletter.getFooter() + "</p>";

			helper.setSubject(subject);

			helper.setText(content, true);

			mailSender.send(message);
		}
	}
}
