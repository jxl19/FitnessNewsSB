package com.fitnessnews.repository;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.fitnessnews.models.Newsletters;

@Repository
public interface NewsletterDAO{
		public Boolean createNewsletter(int newsletterID, Date datePublished, String header, String authFirstName, String authLastName, String content, String footer);
		public Boolean updateNewsletter(int newsletterID, Date datePublished, String header, String authFirstName, String authLastName, String content, String footer);
		public Newsletters displayNewletter(int newsletterID);
}
