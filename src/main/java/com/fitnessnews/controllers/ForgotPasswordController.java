package com.fitnessnews.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.*;

import com.fitnessnews.exceptions.ResourceNotFoundException;
import com.fitnessnews.models.Users;
import com.fitnessnews.services.UserServices;
import com.fitnessnews.utilities.Utility;

import net.bytebuddy.utility.RandomString;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reset")
public class ForgotPasswordController {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private UserServices userService;

	@PostMapping("/forgotpass")
	public String processForgotPassword(HttpServletRequest request, Model model, @RequestBody String email) {
		String token = RandomString.make(30);
		try {
			userService.updateResetPasswordToken(token, email);
			String resetPasswordLink = "http://localhost:4200/resetpass?token=" + token;
			sendEmail(email, resetPasswordLink);
			model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

		} catch (ResourceNotFoundException ex) {
			model.addAttribute("error", ex.getMessage());
		} catch (UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error", "Error while sending email");
		}
		return "working";
	}

	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("testserverrev@gmail.com", "FitnessNews Support");
		helper.setTo(recipientEmail);

		String subject = "Here's the link to reset your password";

		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + link
				+ "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

	@PostMapping("/resetpass")
	public String processResetPassword(HttpServletRequest request, Model model, @RequestBody String userDetails) {
		

		JSONObject obj = new JSONObject(userDetails);
		String password = obj.getString("password");
		String token = obj.getString("token");

		
		  Users user = userService.getByResetPasswordToken(token);
		  model.addAttribute("title", "Reset your password");
		  
		  if (user == null) { model.addAttribute("message", "Invalid Token"); return
		  "message"; } else { userService.updatePassword(user, password);
		  
		  model.addAttribute("message",
		  "You have successfully changed your password."); }
		  
		  return "message";
	}
}