package com.katjarboe.archaeolog.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.katjarboe.archaeolog.models.LoginUser;
import com.katjarboe.archaeolog.models.User;
import com.katjarboe.archaeolog.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	public User register(User newUser, BindingResult result) {
		//find user in db by email
		Optional<User> optionalUser = userRepo.findByEmail(newUser.getEmail());
		//if email is already present, reject
		if(optionalUser.isPresent()) {
			result.rejectValue("email", "unique", "This email is already registered");
		}
		//reject if password doesn't match confirm pass
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("password", "matches", "Passwords must match");
		}
		if (result.hasErrors()) {
			return null; 
		}
		//hash and set password and save user to db
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		userRepo.save(newUser);
		return newUser;
	}
		
	public User login(LoginUser newLogin, BindingResult result) {
		//Find user in the db by email
		Optional<User> optionalUser = userRepo.findByEmail(newLogin.getEmail());
		//if the email is not present, reject
		if(!optionalUser.isPresent()) {
			result.rejectValue("email", "unique", "This email is not registered");
			return null;
		}
		User user = optionalUser.get();
		//if bcrypt password match fails
		if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
			result.rejectValue("password", "matches", "Invalid password.");
		}
		//if result has errors, return null
		if(result.hasErrors()) {
			return null;
		}
		//otherwise, return user object
		return user;
	}
	
	//find one user
	public User oneUser(Long id) {
		Optional<User> optionalUser = userRepo.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}	
	}
}