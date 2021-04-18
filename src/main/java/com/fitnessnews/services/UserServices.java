package com.fitnessnews.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitnessnews.exceptions.ResourceNotFoundException;
import com.fitnessnews.models.Users;
import com.fitnessnews.repository.UsersRepository;

@Service
@Transactional
public class UserServices { 
    @Autowired
    private UsersRepository userRepo;
     
 
    public void updateResetPasswordToken(String token, String email) throws ResourceNotFoundException {
        Users user = userRepo.findByEmail(email);
        System.out.println("in token method" + email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepo.save(user);
        } else {
            throw new ResourceNotFoundException("Could not find any user with the email " + email);
        }
    }
     
    public Users getByResetPasswordToken(String token) {
        return userRepo.findByResetPasswordToken(token);
    }
     
    public void updatePassword(Users user, String newPassword) {
        user.setPassword(newPassword);
         
        user.setResetPasswordToken(null);
        userRepo.save(user);
    }

}
