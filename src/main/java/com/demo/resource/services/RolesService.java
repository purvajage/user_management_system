package com.demo.resource.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.resource.entities.Roles;
import com.demo.resource.entities.User;
import com.demo.resource.exceptions.DuplicateRoleException;
import com.demo.resource.exceptions.RoleNotFoundException;
import com.demo.resource.exceptions.UserNotFoundException;
import com.demo.resource.repositories.RolesRepository;
import com.demo.resource.repositories.UserRepository;

@Service
public class RolesService {
	
	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	UserRepository userRepository;

	public Roles assignRoleToUser(Long userId, Roles roles){
	if(userRepository.findById(userId).isEmpty())
	{
		throw new UserNotFoundException("User is not present for id: "+userId);
	}
	User userdata=userRepository.findById(userId).get();
	if(!rolesRepository.existsByRoleNameAndUser(roles.getRoleName(), userdata))
	{
		roles.setUser(userdata);
		return rolesRepository.save(roles);
	}
	else {
		throw new DuplicateRoleException("oops! this role is already assigned earlier.");
	}
	}

	public List<Roles> fetchAllRolesForUser(Long userId) {
		if(userRepository.findById(userId).isEmpty())
		{
			throw new UserNotFoundException("User is not present for id: "+userId);
		}
		User userdata=userRepository.findById(userId).get();
		return userdata.getRoles();
	}
	
	public Roles fetchRoleByIdForUser(Long userId,Long roleId) {
		Roles roledata=null;
		if(userRepository.findById(userId).isPresent())
		{
			User user=userRepository.findById(userId).get();
			if(rolesRepository.findById(roleId).isPresent())
			{
			  roledata=rolesRepository.findById(roleId).get();
			}
			else {
				throw new RoleNotFoundException("Role is not present for id: "+roleId);
			}
		}
		else {
			throw new UserNotFoundException("User is not present for id: "+userId);
		}
		return roledata;
	}

	public Roles updateRoleByIdForUser(Long userId, Long roleId, Roles role) {
		if(userRepository.findById(userId).isPresent())
		{
			if(rolesRepository.findById(roleId).isPresent())
			{
			  User userdata=userRepository.findById(userId).get();
			  Roles existingRoleWithSameName = rolesRepository.findByRoleNameAndUser(role.getRoleName(), userdata);
	            
	            if (existingRoleWithSameName == null || existingRoleWithSameName.getRoleId().equals(roleId)) {
	                role.setUser(userdata);
	                role.setRoleId(roleId);
	            }
				else {
					throw new DuplicateRoleException("oops! this role is already assigned earlier.");
				}
			}
			else {
				throw new RoleNotFoundException("Role is not present for id: "+roleId);
			}
		}
		else {
			throw new UserNotFoundException("User is not present for id: "+userId);
		}
		return rolesRepository.save(role);
	}
	
	public void deleteRoleByIdForUser(Long userId, Long roleId) {
		if(userRepository.findById(userId).isPresent())
		{
			if(rolesRepository.findById(roleId).isPresent())
			{
				rolesRepository.deleteById(roleId);
			}
			else {
				throw new RoleNotFoundException("Role is not present for id: "+roleId);
			}
		}
		else {
			throw new UserNotFoundException("User is not present for id: "+userId);
		}
	}
	
	public List<Roles> assignMultipleRolesToUser(Long userId, List<Roles> roles) {
	    if (userRepository.findById(userId).isEmpty()) {
	        throw new UserNotFoundException("User is not present for id: " + userId);
	    }

	    User userdata = userRepository.findById(userId).get();

	    List<Roles> rolesdata = new ArrayList<>();

	    for (Roles role : roles) {
	        if (!rolesRepository.existsByRoleNameAndUser(role.getRoleName(), userdata)) {
	            role.setUser(userdata);
	            rolesdata.add(rolesRepository.save(role));
	        } else {
	            throw new DuplicateRoleException("You can only assign one " + role.getRoleName() + " role to user");
	        }
	    }

	    return rolesdata;
	}


}
