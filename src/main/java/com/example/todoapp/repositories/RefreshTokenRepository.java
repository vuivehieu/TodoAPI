package com.example.todoapp.repositories;

import com.example.todoapp.entities.RefreshToken;
import com.example.todoapp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    @Override
    Optional<RefreshToken> findById(Long id);
    Optional<RefreshToken> findByToken(String token);
    int deleteByUser(UserEntity user);
}
