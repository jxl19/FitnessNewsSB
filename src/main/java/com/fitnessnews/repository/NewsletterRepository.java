package com.fitnessnews.repository;

import javax.persistence.Column;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitnessnews.models.NewsletterSet;
import com.fitnessnews.models.Newsletters;

@Repository
public interface NewsletterRepository extends JpaRepository<Newsletters, Long> {
	
	public Newsletters findBynewsletterID(Long newsletterID);
	
	@Query("SELECT NEW com.fitnessnews.models.NewsletterSet(newsletterID, datePublished, header, authFirstName, authLastName, content, footer) FROM Newsletters WHERE newsletterID = (SELECT max(newsletterID) FROM Newsletters)")
	public NewsletterSet getLatestNewsletter();
}
