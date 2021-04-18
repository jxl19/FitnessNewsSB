package com.fitnessnews.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitnessnews.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	public Users findByEmail(String email);
	public Users findByResetPasswordToken(String token);
}
