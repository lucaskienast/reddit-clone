package com.kienast.reddit.redditclonespringboot.exception;

public class SubredditNotFoundException extends RuntimeException {

    public SubredditNotFoundException(String message) {
        super(message);
    }
}
