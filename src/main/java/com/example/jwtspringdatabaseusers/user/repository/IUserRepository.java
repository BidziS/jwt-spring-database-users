package com.example.jwtspringdatabaseusers.user.repository;

import com.example.jwtspringdatabaseusers.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.email =?1")
    UserEntity findByEmail(String email);
}
