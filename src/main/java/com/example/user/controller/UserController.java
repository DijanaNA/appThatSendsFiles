package com.example.user.controller;

import java.util.Collections;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.entity.ApiResponse;
import com.example.user.entity.Role;
import com.example.user.entity.RoleName;
import com.example.user.entity.User;
import com.example.user.repository.RoleRepository;
import com.example.user.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	
	//create user with admin role
	@PostMapping("/signupAdmin")
	public ResponseEntity<?> registerAdministrator(@RequestBody User user) {

		if (userRepository.existsByUsername(user.getUsername())) {

			User existingUser = userRepository.findByUsername(user.getUsername());

//			return ResponseEntity.badRequest().body(existingUser);
			return new ResponseEntity(new ApiResponse(false, "User with username " + user.getUsername() +" already exists"), HttpStatus.BAD_REQUEST);
			
		}

		if (userRepository.existsByEmail(user.getEmail())) {

			User existingUser = userRepository.findByEmail(user.getEmail());

//			return ResponseEntity.badRequest().body(existingUser);
			return new ResponseEntity(new ApiResponse(false, "User with email " + user.getEmail() +" already exists"), HttpStatus.BAD_REQUEST);

		}

		Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
		if (userRole != null) {
			user.setRoles(Collections.singleton(userRole));
		}
		userRepository.save(user);

//		return ResponseEntity.ok(user);
		return new ResponseEntity(new ApiResponse(true, "User with username " + user.getUsername() +" successfully created"), HttpStatus.OK);
		

	}
	
	
	
	//create user with user role
	@PostMapping("/signupUser")
	public ResponseEntity<?> registerUser(@RequestBody User user) {

		if (userRepository.existsByUsername(user.getUsername())) {

			User existingUser = userRepository.findByUsername(user.getUsername());

//			return ResponseEntity.badRequest().body(existingUser);
			return new ResponseEntity(new ApiResponse(false, "User with username " + user.getUsername() +" already exists"), HttpStatus.BAD_REQUEST);
			
		}

		if (userRepository.existsByEmail(user.getEmail())) {

			User existingUser = userRepository.findByEmail(user.getEmail());

//			return ResponseEntity.badRequest().body(existingUser);
			return new ResponseEntity(new ApiResponse(false, "User with email " + user.getEmail() +" already exists"), HttpStatus.BAD_REQUEST);

		}

		Role userRole = roleRepository.findByName(RoleName.ROLE_USER);
		if (userRole != null) {
			user.setRoles(Collections.singleton(userRole));
		}
		userRepository.save(user);

//		return ResponseEntity.ok(user);
		return new ResponseEntity(new ApiResponse(true, "User with username " + user.getUsername() +" successfully created"), HttpStatus.OK);
		

	}
	
	
	
	
	
	
	

}
