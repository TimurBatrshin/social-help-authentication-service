package com.example.socialhelp.repositories;

import com.example.socialhelp.models.Token;
import com.example.socialhelp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findTokenByUsers(User user);
}
