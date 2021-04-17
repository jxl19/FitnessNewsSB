package com.fitnessnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitnessnews.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

}
