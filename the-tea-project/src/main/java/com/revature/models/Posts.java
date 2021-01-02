package com.revature.models;



import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "posts", schema = "public") //@Data @AllArgsConstructor @NoArgsConstructor @ToString(exclude = {"users"})
public class Posts implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 189491964075651940L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_post_id", nullable = false)
	private int postId;
	@Column(name = "user_post")
	private String userPost;
	
//	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//	private int userId;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = { CascadeType.ALL})
	@JoinColumn(name = "userId", nullable = false)
	private User user;
	
	@Column(name="likes")
	private int likes;
	
	
//	@Column(name = "user_id")
//	private int userId;
	
	//@Lob
	private String image;
	
//	private List<Users> likes; attached @Table to likes table???

//	public Posts() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public Posts(String userPost, String image) { 
//		super();
//		this.userPost = userPost;
//		this.userId = userId;
//		this.image = image;
//	}
//
//	public int getPostId() {
//		return postId;
//	}
//
//	public void setPostId(int postId) {
//		this.postId = postId;
//	}
//
//	public String getUserPost() {
//		return userPost;
//	}
//
//	public void setUserPost(String userPost) {
//		this.userPost = userPost;
//	}
//
////	public int getUserId() {
////		return userId;
////	}
////
////	public void setUserId(int userId) {
////		this.userId = userId;
////	}
//
//	public String getImage() {
//		return image;
//	}
//
//	public void setImage(String image) {
//		this.image = image;
//	}
//
//	@Override
//	public String toString() {
//		return "Posts [postId=" + postId + ", userPost=" + userPost + ", image=" + image + "]";
//	} //+ ", userId=" + userId
//	
	
	
}
