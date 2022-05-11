package com.kienast.reddit.redditclonespringboot;

import com.kienast.reddit.redditclonespringboot.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class RedditCloneSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditCloneSpringBootApplication.class, args);
	}

}
