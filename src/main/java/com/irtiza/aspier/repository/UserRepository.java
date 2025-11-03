package com.irtiza.aspier.repository;

import com.irtiza.aspier.entity.User;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@NullMarked
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
