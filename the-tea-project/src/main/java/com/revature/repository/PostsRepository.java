package com.revature.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.models.Posts;

@Repository

public interface PostsRepository extends JpaRepository<Posts, Integer> {
	
	@Query(value = "SELECT * FROM posts WHERE user_id=:userId", nativeQuery = true)
	public List<Posts> findByUserId(@Param("userId") int userId);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE posts SET likes=likes+1 WHERE user_post_id=:postId", nativeQuery = true)
	public void getLikes(@Param("postId") int postId);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE posts SET image=:image WHERE user_post_id=:postId", nativeQuery = true)
	public void putInImagesColumn(@Param("postId") int postId, @Param("image") String image);
}
