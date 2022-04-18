package com.example.socialhelp.repositories;

import com.example.socialhelp.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

}
