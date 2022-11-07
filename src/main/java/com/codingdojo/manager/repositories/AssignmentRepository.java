package com.codingdojo.manager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.manager.models.Assignment;
import com.codingdojo.manager.models.User;

@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, Long> {

	List<Assignment> findAll();
	List<Assignment> findAllByUsers(User user);
	List<Assignment> findByUsersNotContains(User user);
	Assignment findByIdIs(Long id);
}
