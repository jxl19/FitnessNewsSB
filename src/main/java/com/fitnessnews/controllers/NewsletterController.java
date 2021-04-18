package com.fitnessnews.controllers;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessnews.models.Newsletters;
import com.fitnessnews.repository.NewsletterDAO;
import com.fitnessnews.util.HibernateUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/newsletter")
public class NewsletterController implements NewsletterDAO {

	@GetMapping("/new")
	@Override
	public Boolean createNewsletter(int newsletterID, Date datePublished, String header, String authFirstName,
			String authLastName, String content, String footer) {
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		Newsletters news = new Newsletters(newsletterID, datePublished, header, authFirstName, authLastName, content, footer);
		    
		    session.save(news);
		    
		    transaction.commit();
		    session.close();
		return null;
	}

	@GetMapping("/update")
	@Override
	public Boolean updateNewsletter(int newsletterID, Date datePublished, String header, String authFirstName,
			String authLastName, String content, String footer) {
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		Newsletters news = session.get(Newsletters.class, newsletterID);//the second field refers to the NewsletterID
		news.setDatePublished(datePublished);  
		news.setHeader(header);
		news.setAuthFirstName(authFirstName);
		news.setAuthLastName(authLastName);
		news.setContent(content);
		news.setFooter(footer);
		
		session.update(news);
		transaction.commit();
	
		return null;
	}

	@GetMapping("/show")
	@Override
	public Newsletters displayNewletter(int newsletterID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Newsletters news = session.get(Newsletters.class, newsletterID);
	
		return news;
	}

}
