package com.example.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user.entity.Role;
import com.example.user.entity.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	Role findByName(RoleName name);

}
