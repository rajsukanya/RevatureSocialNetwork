package com.revature.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.email.Email;
import com.revature.exception.ResourceNotFoundException;
import com.revature.models.User;
import com.revature.repository.UsersRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/thetea")
public class UsersController {
	@Autowired
	private UsersRepository usersRepository;
	
//	@Autowired
//    private static JavaMailSender javaMailSender;
	
	@Autowired
	private static Email email;
	
	@GetMapping("/users")
	public List<User> getAllEmployees() {
		return usersRepository.findAll();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable(value = "id") int userId) {
		Optional<User> users = usersRepository.findById(userId);
		return users;
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name){
		try {
			List<User> users = new ArrayList<User>();
			if(name == null)
				usersRepository.findAll().forEach(users::add);
			else
				usersRepository.findByName(name).forEach(users::add);
			if(users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/{firstName}" , produces=MediaType.APPLICATION_JSON_VALUE) 
	public List<User> findByFirstName(String firstName) {
		return (List<User>) usersRepository.findByName(firstName);

	}
	
	//@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add")
	public User createUser(@Valid @RequestBody User user) {
		User u = usersRepository.save(user);
		return u;
	}
	
	
//	 void sendEmail(String email) {
//
//	        SimpleMailMessage msg = new SimpleMailMessage();
//	        //String email = "sukanyaraj1014@gmail.com";
//	        msg.setTo(email);
//
//	        msg.setSubject("Message from The Tea");
//	        msg.setText("You have requested to reset your password for your The Tea account.");
//
//	        javaMailSender.send(msg);
//
//	 }
	
//	public static void sendEmail(String email) throws Exception{
//    	MimeMessage msg = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(msg);
//        //SimpleMailMessage msg = new SimpleMailMessage();
//        //String email = "sukanyaraj1014@gmail.com";
//        try {
//			helper.setTo(email);
//			helper.setSubject("Message from The Tea");
//	        helper.setText("You have requested to reset your password for your The Tea account.");
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        javaMailSender.send(msg);
//    }
	
	@PostMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") int userId,	@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		User user = usersRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		//Email email = new Email();
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setUsername(userDetails.getUsername());
		user.setPassword(userDetails.getPassword());
		String test = userDetails.getProfilepicture();
		String set = test.substring(1, test.length()-1);
		user.setProfilepicture(set);
		user.setEmail(userDetails.getEmail());
		System.out.println("email: " + user.getEmail());
		try {
			email.sendEmail(user.getEmail());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if(!userDetails.getPassword().equals(user.getPassword())) {
//			Email.sendEmail(user.getEmail());
//			System.out.println("Email sent");
//		}
		final User updatedUser = usersRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") int userId)
			throws ResourceNotFoundException {
		User user = usersRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		usersRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
