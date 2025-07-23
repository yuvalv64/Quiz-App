package com.quiz.repository;

import com.quiz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // findByEmail is Query Derivation by Method Name (QDMN)
    Optional<User> findByEmail(String email);
}
