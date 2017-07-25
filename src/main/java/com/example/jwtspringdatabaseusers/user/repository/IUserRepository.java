package com.example.jwtspringdatabaseusers.user.repository;

import com.example.jwtspringdatabaseusers.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email =?1")
    User findByEmail(String email);

    @Query("SELECT u FROM User u JOIN u.authorities a WHERE a.name LIKE ?1")
    List<User> findAllByAuthority(String name);



}
