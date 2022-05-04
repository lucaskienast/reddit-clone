package com.kienast.reddit.redditclonespringboot.repository;

import com.kienast.reddit.redditclonespringboot.model.Comment;
import com.kienast.reddit.redditclonespringboot.model.Post;
import com.kienast.reddit.redditclonespringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);

}
