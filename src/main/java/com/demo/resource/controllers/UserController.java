package com.demo.resource.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.resource.entities.User;
import com.demo.resource.helper.ResponseWrapper;
import com.demo.resource.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	ResponseWrapper wrapper=new ResponseWrapper();

	@GetMapping("/allusers")
	public ResponseEntity<List<User>> fetchAllUsers(){
		wrapper.setMessage("All users data");
		wrapper.setData(userService.fetchallUsers());
		return new ResponseEntity(wrapper,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> fetchUserById(@PathVariable("id") Long userId) throws Exception {
		wrapper.setMessage("user data for id: "+userId);
		wrapper.setData(userService.getUserById(userId));
		return new ResponseEntity(wrapper,HttpStatus.OK);
	}
	
	@PostMapping("/createuser")
	public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
		wrapper.setMessage("User created successfully");
		wrapper.setData(userService.createUser(user));
		return new ResponseEntity(wrapper,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateuser/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,@RequestBody @Valid User user) {
		wrapper.setMessage("user updated successfully with id: "+userId);
		wrapper.setData(userService.updateUserById(userId, user));
		return new ResponseEntity(wrapper,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity deleteUserById(@PathVariable("id") Long userId) throws Exception {
		if(userService.getUserById(userId).get()==null)
		{
			wrapper.setMessage("user not found for id: "+userId);
		}
		wrapper.setMessage("user deleted successfully for id: "+userId);
		userService.deleteUserById(userId);
		return new ResponseEntity(wrapper,HttpStatus.OK);
	}
	
	@PostMapping("/addmulusers")
	public ResponseEntity<List<User>> createMultipleUsers(@RequestBody @Valid List<User> users){
		wrapper.setMessage("All users created successfully.");
		wrapper.setData(userService.createMultipleUsers(users));
		return new ResponseEntity(wrapper,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteallusers")
	public ResponseEntity<List<User>> deleteAllUsers(){
		wrapper.setMessage("All users deleted successfully.");
		userService.deleteAllUsers();
		return new ResponseEntity(wrapper,HttpStatus.OK);
	}
	
	@PostMapping("/user/{id}/uploadpic")
	public ResponseEntity<User> uploadUserPic(@RequestParam("image")MultipartFile file,
			                                    @PathVariable("id") Long userId) throws IOException {
		wrapper.setMessage("profile picture uploaded for user with id: "+userId);
		wrapper.setData(userService.uploadUserPic(file, userId));
		return new ResponseEntity(wrapper,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{id}/downloadpic")
	public ResponseEntity<byte[]> downloadUserPic(@PathVariable("id") Long userId) {
		byte[] userPic=userService.downloadUserPic(userId);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(userPic);
	}
	
	
}
