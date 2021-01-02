package com.revature.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exception.ResourceNotFoundException;
import com.revature.models.Posts;
import com.revature.models.User;
import com.revature.repository.PostsRepository;
import com.revature.repository.UsersRepository;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials ="true")
@RestController
@RequestMapping("/theteaPost")
public class PostsController {
	@Autowired
	private PostsRepository postsRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private UsersController usersController;
	
	//@GetMapping("/posts")
	//@Query(value = "select * from posts")
	@GetMapping("/posts")
	public List<Posts> getAllPosts() {
		return postsRepository.findAll();
	}
	
//	@PutMapping()
//	public Posts putPost() {
//		return new Posts();
//	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/posts/{id}")
	public List<Posts> getPostById(@PathVariable(value = "id") int userId) {
		List<Posts> posts = postsRepository.findByUserId(userId);
		System.out.println("in getPostById " + posts);
		return posts;
	}
	
	
	
	//changing method name to createPost instead of createUser
//	//@ResponseStatus(HttpStatus.CREATED)
//	@PostMapping("/addPost/{user_id}")
//	public Posts createPost(@PathVariable(value = "user_id")int userId,@Valid @RequestBody Posts post) {
//		System.out.println("POST IS HERE " + 
//	post);
//		return postsRepository.save(post);
//	}
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/addPost/{userId}")
	public Posts createPost(@PathVariable(value="userId") int userId, @Valid @RequestBody Posts post) {
		System.out.println("POST IS HERE " + post);
		User user=usersRepository.getOne(userId);
		post.setUser(user);
		String test = post.getImage();
		String set = test.substring(1, test.length()-1);
		post.setImage(set);
		System.out.println("POST ID: " + post.getPostId());
		return postsRepository.save(post);
	}
	
	//This works
//	@ResponseStatus(HttpStatus.CREATED)
//	@PostMapping("/addPost")
//	public Posts createPost(@Valid @RequestBody Posts post) {
//		System.out.println("POST IS HERE " + 
//	post);
//		return postsRepository.save(post);
//	}

	
	@PutMapping("/updatePosts/{id}")
	public ResponseEntity<Posts> updatePost(@PathVariable(value = "id") int postId,	@Valid @RequestBody Posts postDetails) throws ResourceNotFoundException {
		Posts post = postsRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + postId));

		post.setUserPost(postDetails.getUserPost());
		post.setImage(postDetails.getImage());
		final Posts updatedPost = postsRepository.save(post);
		return ResponseEntity.ok(updatedPost);
	}

	@DeleteMapping("/deletePosts/{id}")
	public Map<String, Boolean> deletePost(@PathVariable(value = "id") int postId)
			throws ResourceNotFoundException {
		Posts post = postsRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + postId));

		postsRepository.delete(post);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@PostMapping("/likes/{postId}")
	public void getLikesById(@PathVariable(value = "postId") int postId) throws GenericJDBCException{
		postsRepository.getLikes(postId);
//		System.out.println("in getLikesById " + maxLikes);
		try {
			
		}
		catch(GenericJDBCException e){
			e.getSQLException();
			e.printStackTrace();
		}
		
		//return maxLikes;
	}
}
