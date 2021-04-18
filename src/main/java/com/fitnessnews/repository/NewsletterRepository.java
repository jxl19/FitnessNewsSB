package com.fitnessnews.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitnessnews.models.Newsletters;

@Repository
public interface NewsletterRepository extends JpaRepository<Newsletters, Long>{

}
