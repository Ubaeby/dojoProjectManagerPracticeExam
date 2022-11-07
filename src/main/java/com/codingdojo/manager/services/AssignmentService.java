package com.codingdojo.manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.manager.models.Assignment;
import com.codingdojo.manager.models.User;
import com.codingdojo.manager.repositories.AssignmentRepository;

@Service
public class AssignmentService {

	@Autowired
	private AssignmentRepository assignment;
	
	public Assignment addAssign(Assignment a) {
		return assignment.save(a);
	}
	
	public Assignment updateAssign(Assignment a) {
		return assignment.save(a);
	}
	
	public void deleteAssign(Assignment a) {
		assignment.delete(a);
	}
	
	public List<Assignment> allAssign() {
		return assignment.findAll();
	}
	
	public Assignment findAssignmentId(Long id) {
		Optional<Assignment> option = assignment.findById(id);
		if (option.isPresent()) {
			return option.get();
		}
		return null;
	}
	
	public List<Assignment> assignedUser(User u) {
		return assignment.findAllByUsers(u);
	}
	
	public List<Assignment> unassignedUser(User u) {
		return assignment.findByUsersNotContains(u);
	}
	
}
