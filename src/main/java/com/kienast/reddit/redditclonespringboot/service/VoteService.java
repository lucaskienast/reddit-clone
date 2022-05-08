package com.kienast.reddit.redditclonespringboot.service;

import com.kienast.reddit.redditclonespringboot.dto.VoteDto;
import com.kienast.reddit.redditclonespringboot.exception.PostNotFoundException;
import com.kienast.reddit.redditclonespringboot.exception.SpringRedditException;
import com.kienast.reddit.redditclonespringboot.model.Post;
import com.kienast.reddit.redditclonespringboot.model.Vote;
import com.kienast.reddit.redditclonespringboot.model.VoteType;
import com.kienast.reddit.redditclonespringboot.repository.PostRepository;
import com.kienast.reddit.redditclonespringboot.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post not found with id - " + voteDto.getPostId()));

        Optional<Vote> voteByPostAndUser = voteRepository
                .findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());

        if (voteByPostAndUser.isPresent()
                && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {

            throw new SpringRedditException("You have already " + voteDto.getVoteType() + "'d for this post");
        }

        if (VoteType.UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }

        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }

}
