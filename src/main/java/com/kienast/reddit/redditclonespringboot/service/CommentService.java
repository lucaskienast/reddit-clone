package com.kienast.reddit.redditclonespringboot.service;

import com.kienast.reddit.redditclonespringboot.dto.CommentsDto;
import com.kienast.reddit.redditclonespringboot.exception.PostNotFoundException;
import com.kienast.reddit.redditclonespringboot.mapper.CommentMapper;
import com.kienast.reddit.redditclonespringboot.model.Comment;
import com.kienast.reddit.redditclonespringboot.model.NotificationEmail;
import com.kienast.reddit.redditclonespringboot.model.Post;
import com.kienast.reddit.redditclonespringboot.model.User;
import com.kienast.reddit.redditclonespringboot.repository.CommentRepository;
import com.kienast.reddit.redditclonespringboot.repository.PostRepository;
import com.kienast.reddit.redditclonespringboot.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CommentService {

    private static final String POST_URL = "";

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AuthService authService;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
    private final UserRepository userRepository;

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));

        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(post.getUser().getUsername() + "posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail((new NotificationEmail(user.getUsername() + "commented on your post", user.getEmail(), message)));
    }

    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));

        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

    public List<CommentsDto> getAllCommentsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

}
