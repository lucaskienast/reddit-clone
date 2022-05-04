package com.kienast.reddit.redditclonespringboot.repository;

import com.kienast.reddit.redditclonespringboot.model.Post;
import com.kienast.reddit.redditclonespringboot.model.User;
import com.kienast.reddit.redditclonespringboot.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);

}
