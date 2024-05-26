package com.demo.resource.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.resource.entities.Roles;
import com.demo.resource.entities.User;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

	boolean existsByRoleNameAndUser(String roleName,User user);

	Roles findByRoleNameAndUser(String roleName, User userdata);

}
