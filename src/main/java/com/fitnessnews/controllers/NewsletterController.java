package com.fitnessnews.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessnews.models.Newsletters;
import com.fitnessnews.repository.NewsletterRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Newsletter")
public class NewsletterController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private NewsletterRepository newsletterRepository;
	
	@PostMapping("/createnewsletter")
    public Newsletters createNewsletter(@Valid @RequestBody Newsletters newsletter) {
        return newsletterRepository.save(newsletter);
    }
	
	public void sendNewsletter(String recipientEmail, URL link) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		Newsletters newsletter = newsletterRepository.getLatestNewsletter();

		helper.setFrom("testserverrev@gmail.com", "FitnessNews");
		helper.setTo(recipientEmail);

		String subject = newsletter.getHeader();

		String content = "<h3>" + newsletter.getHeader() + "</h3><br><p>By: " + newsletter.getAuthFirstName() + " " + newsletter.getAuthLastName() +
				"</p><br><p>" + newsletter.getDatePublished() + "</p><br><br><p>" + newsletter.getContent() + "</p><br><br><br>" + newsletter.getFooter()
				;

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

}
