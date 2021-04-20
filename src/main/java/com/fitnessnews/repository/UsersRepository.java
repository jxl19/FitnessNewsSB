package com.fitnessnews.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitnessnews.models.ResultSet;
import com.fitnessnews.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
	public Users findByEmail(String email);
	public Users findByResetPasswordToken(String token);
	
	@Query("SELECT NEW com.fitnessnews.models.ResultSet(l.userID, l.email, u.wantsMail) FROM Users l INNER JOIN PersonalInfo u ON l.userID = u.userID WHERE u.wantsMail = true")
	public List<ResultSet> getListOfSubscribers();
}
