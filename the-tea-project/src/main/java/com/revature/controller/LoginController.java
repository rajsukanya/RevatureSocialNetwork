package com.revature.controller;

import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exception.ResourceNotFoundException;
import com.revature.models.User;
import com.revature.repository.UsersRepository;

@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials ="true")
@RestController
@RequestMapping("/thetea")
public class LoginController {
	@Autowired
	private UsersRepository userRepository;
	
	@GetMapping("")
	private List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//changes made by sf: RequestBody comes back as a single JSON and to read value needs to be set to bean, changed Strings to User object
	//changes made by sf: when iterating while loop would hit the first user, which did not meet the boolean set in if, then would hit else
	//and code would exit while loop throwing ResourceNotFoundException. to get through all users took out else and put Exception outside while
	//if wrong login ResourceNotFoundException will get thrown still
	//there is opportunity to optimize this code but as it is now, it will return a login
	
	@PostMapping("/login")
	public User login(@Valid @RequestBody User loginUser) throws ResourceNotFoundException {
		System.out.println(loginUser.getUsername());
		List<User> userList = getAllUsers();
		User user = new User();
		Iterator<User> iterator = userList.iterator();
		while(iterator.hasNext()) {
			System.out.println(userList.iterator());
			user = iterator.next();
			System.out.println(user.getUsername());
			if(user.getUsername().equals(loginUser.getUsername()) && user.getPassword().equals(loginUser.getPassword())) {
				return user;
			}
		}
		throw new ResourceNotFoundException("Login incorrect");
	}
}