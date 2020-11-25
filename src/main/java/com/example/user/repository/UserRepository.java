package com.example.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	

}
