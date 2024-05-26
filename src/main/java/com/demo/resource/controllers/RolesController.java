package com.demo.resource.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.resource.entities.Roles;
import com.demo.resource.helper.ResponseWrapper;
import com.demo.resource.services.RolesService;
import com.demo.resource.services.UserService;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

	@Autowired
	private RolesService roleService;
	
	@Autowired
	private UserService userService;
	
	ResponseWrapper wrapper=new ResponseWrapper();
	
	@PostMapping("/assignrole/user/{id}")
	public ResponseEntity assignRoleToUser(@PathVariable("id")Long userId,@RequestBody Roles roles) throws Exception {
		wrapper.setMessage("Role assigned successfully to user with id: "+userId);
		wrapper.setData(roleService.assignRoleToUser(userId,roles));
		return new ResponseEntity(wrapper,HttpStatus.CREATED);
	}
	
	@GetMapping("/allroles/user/{id}")
	public ResponseEntity fetchAllRolesForUser(@PathVariable("id")Long userId) {
		wrapper.setMessage("Roles to user with id: "+userId);
		wrapper.setData(roleService.fetchAllRolesForUser(userId));
		return new ResponseEntity(wrapper,HttpStatus.OK);	
	}
	
	@GetMapping("/user/{id}/role/{roleId}")
	public ResponseEntity fetchRoleByIdForUser(@PathVariable("id")Long userId,@PathVariable("roleId")Long roleId) {
	    wrapper.setMessage("Role with id: "+roleId+" for user with id: "+userId);
		wrapper.setData(roleService.fetchRoleByIdForUser(userId, roleId));
		return new ResponseEntity(wrapper,HttpStatus.OK);	
	}
	
	@PutMapping("/user/{id}/role/{roleId}/update")
	public ResponseEntity updateRoleByIdForUser(@PathVariable("id")Long userId,@PathVariable("roleId")Long roleId,@RequestBody Roles role) {
	    wrapper.setMessage("Role with id: "+roleId+" updated successfully for user with id: "+userId);
		wrapper.setData(roleService.updateRoleByIdForUser(userId,roleId,role));
		return new ResponseEntity(wrapper,HttpStatus.OK);	
	}
	
	@DeleteMapping("/user/{id}/role/{roleId}/delete")
	public ResponseEntity deleteRoleByIdForUser(@PathVariable("id")Long userId,@PathVariable("roleId")Long roleId) {
	    wrapper.setMessage("Role with id: "+roleId+" deleted successfully for user with id: "+userId);
		roleService.deleteRoleByIdForUser(userId, roleId);
		return new ResponseEntity(wrapper,HttpStatus.OK);	
	}
	
	@PostMapping("/assignroles/user/{id}")
	public ResponseEntity assignMultipleRolesToUser(@PathVariable("id")Long userId,@RequestBody List<Roles> roles) throws Exception {
		wrapper.setMessage("Role assigned successfully to user with id: "+userId);
		wrapper.setData(roleService.assignMultipleRolesToUser(userId,roles));
		return new ResponseEntity(wrapper,HttpStatus.CREATED);
	}
}
