package com.kienast.reddit.redditclonespringboot.repository;

import com.kienast.reddit.redditclonespringboot.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {

    List<Subreddit> findByName(String subredditName);

}
