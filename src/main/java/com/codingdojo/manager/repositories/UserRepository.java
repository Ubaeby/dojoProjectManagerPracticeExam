package com.codingdojo.manager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.manager.models.Assignment;
import com.codingdojo.manager.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	List<User> findAll();
	Optional<User> findByEmail(String email);
	List<User> findByIdIs(Long id);
	List<User> findAllByAssignments(Assignment assignment);
	List<User> findByAssignmentsNotContains(Assignment assignment);
}
