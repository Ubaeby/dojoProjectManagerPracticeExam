package com.codingdojo.manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.manager.models.Assignment;
import com.codingdojo.manager.models.Task;
import com.codingdojo.manager.repositories.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository task;
	
	public List<Task> allTask() {
		return task.findAll();
	}
	
	public Task createTask(Task t) {
		return task.save(t);
	}
	
	public Task updateTask(Task t) {
		return task.save(t);
	}
	
	public Task findTask(Long id) {
		Optional<Task> option = task.findById(id);
		if (option.isPresent()) {
			return option.get();
		}
		return null;
	}
	
	public List<Task> tasksInAssignment(Long id) {
		return task.findByAssignmentIdIs(id);
	}
	
	public void addToAssignment(Task t, Assignment a) {
		t.setAssignment(a);
		task.save(t);
	}
}
