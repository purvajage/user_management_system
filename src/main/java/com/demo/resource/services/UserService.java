package com.demo.resource.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.demo.resource.entities.User;
import com.demo.resource.exceptions.UserNotFoundException;
import com.demo.resource.helper.ImageUtils;
import com.demo.resource.repositories.UserRepository;



@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User createUser(User user) {
	     return userRepository.save(user);
	}
	
	public Optional<User> getUserById(Long userId) {
		if(userRepository.findById(userId).isEmpty())
		{
			throw new UserNotFoundException("User not found for id: "+userId);
		}
		System.out.println(userRepository.findById(userId).get());
		return userRepository.findById(userId);
	}
	
	public List<User> fetchallUsers(){
		List<User> users=new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		System.out.println(users);
		return users;
	}
	
	public void deleteUserById(Long userId) throws Exception {
		if(userRepository.findById(userId).isEmpty())
		{
			throw new UserNotFoundException("User not found for id: "+userId);
		}
		System.out.println(userRepository.findById(userId).get());
		userRepository.deleteById(userId);
	}
	
	public User updateUserById(Long userId,User user) {
		if(userRepository.findById(userId).isEmpty())
		{
			throw new UserNotFoundException("User not found for id: "+userId);
		}
		user.setUserId(userId);
		return userRepository.save(user);
	}
	
	public void deleteAllUsers() {
		userRepository.deleteAll();
		System.out.println("all users deleted successfully.");
	}
	
	public List<User> createMultipleUsers(List<User> users) {
		List<User> userlist = new ArrayList<>();
		for(User user:users) {
			userlist.add(user);
		}
		return userRepository.saveAll(userlist);
	}

	public User uploadUserPic(MultipartFile file,Long userId) throws IOException {
		if(userRepository.findById(userId).isEmpty())
		{
			throw new UserNotFoundException("User not found for id: "+userId);
		}		
		User user=userRepository.findById(userId).get();
		user.setUserId(userId);
		User userprofile=User.builder().userPic(ImageUtils.compressImage(file.getBytes())).build();
		user.setUserPic(userprofile.getUserPic());
		return userRepository.save(user);
	}
	
	 public byte[] downloadUserPic(Long userId){
		 if(userRepository.findById(userId).isEmpty())
			{
				throw new UserNotFoundException("User not found for id: "+userId);
			}		
			User user=userRepository.findById(userId).get();
	        byte[] images=ImageUtils.decompressImage(user.getUserPic());
	        return images;
	    }
	
}
