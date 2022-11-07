package com.codingdojo.manager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.manager.models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

	List<Task> findAll();
	List<Task> findByAssignmentIdIs(Long id);
}
