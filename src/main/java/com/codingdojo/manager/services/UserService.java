package com.codingdojo.manager.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.manager.models.Assignment;
import com.codingdojo.manager.models.LoginUser;
import com.codingdojo.manager.models.User;
import com.codingdojo.manager.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository user;
	
	public User register(User newUser, BindingResult result) {
		Optional<User> possibleUser = user.findByEmail(newUser.getEmail());
		if (possibleUser.isPresent()) {
			result.rejectValue("email", "Matches", "Email has been taken");
		}
		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "matches", "Passwords must match");
		}
		if (result.hasErrors()) {
			return null;
		}
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()); 
		newUser.setPassword(hashed);
		return user.save(newUser);
	}
	
	
	public User login(LoginUser newLogin, BindingResult result) {
		Optional<User> possibleUser = user.findByEmail(newLogin.getEmail());
		
		if (!possibleUser.isPresent()) {
			result.rejectValue("email", "Matches", "User not found");
			return null;
		}
		
		User nUser = possibleUser.get();
		if (!BCrypt.checkpw(newLogin.getPassword(), nUser.getPassword() )) {
			result.rejectValue("password", "Matches", "Wrong password buddy");
		}
		if (result.hasErrors()) {
			return null;
		}
		return nUser;
	}
	
	public List<User> allUsers() {
		return user.findAll();
	}
	
	public User updateUser(User u) {
		return user.save(u);
	}
	
	public List<User> assignedAssignments(Assignment a) {
		return user.findAllByAssignments(a);
	}
	
	public List<User> unassignedAssignments(Assignment a) {
		return user.findByAssignmentsNotContains(a);
	}
	
	public User findUserId(Long id) {
		Optional<User> option = user.findById(id);
		if (option.isPresent()) {
			return option.get();
		}
		return null;
		
	}
}
