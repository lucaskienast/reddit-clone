package com.kienast.reddit.redditclonespringboot.repository;

import com.kienast.reddit.redditclonespringboot.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    List<VerificationToken> findByToken(String token);

}
