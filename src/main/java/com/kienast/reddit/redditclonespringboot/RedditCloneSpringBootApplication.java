package com.kienast.reddit.redditclonespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RedditCloneSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditCloneSpringBootApplication.class, args);
	}

}
