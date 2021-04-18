package com.fitnessnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitnessnews.models.Newsletters;

@Repository
public interface NewsletterRepository extends JpaRepository<Newsletters, Long> {
	
	public Newsletters findByID(Integer newsletterID);
	
	@Query("SELECT max(newsletterID) FROM newsletter")
	public Newsletters getLatestNewsletter();
}
