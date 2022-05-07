package com.kienast.reddit.redditclonespringboot.service;

import com.kienast.reddit.redditclonespringboot.dto.SubredditDto;
import com.kienast.reddit.redditclonespringboot.exception.SpringRedditException;
import com.kienast.reddit.redditclonespringboot.mapper.SubredditMapper;
import com.kienast.reddit.redditclonespringboot.model.Subreddit;
import com.kienast.reddit.redditclonespringboot.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit subreddit = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setSubredditId(subreddit.getSubredditId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(toList());
    }

    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with id - " + id));

        return subredditMapper.mapSubredditToDto(subreddit);
    }

}
