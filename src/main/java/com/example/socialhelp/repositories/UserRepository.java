package com.example.socialhelp.repositories;

import com.example.socialhelp.models.Token;
import com.example.socialhelp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);

    Optional<User> findByEmail(String email);

}
