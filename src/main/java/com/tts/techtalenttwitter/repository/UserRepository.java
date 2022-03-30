package com.tts.techtalenttwitter.repository;

import com.tts.techtalenttwitter.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>  {
	// This specifies that we are going to need to query by username
	User findByUsername(String username);
}
