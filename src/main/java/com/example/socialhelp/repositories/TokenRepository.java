package com.example.socialhelp.repositories;

import com.example.socialhelp.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findTokenByRefreshToken(String token);

    Optional<Token> findTokenById(Long id);


}
