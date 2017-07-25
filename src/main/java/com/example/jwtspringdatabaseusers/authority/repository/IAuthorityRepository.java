package com.example.jwtspringdatabaseusers.authority.repository;

import com.example.jwtspringdatabaseusers.authority.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Bidzis on 7/24/2017.
 */
public interface IAuthorityRepository extends JpaRepository<Authority, Long> {
    @Query("SELECT a FROM Authority a WHERE a.name =?1")
    Authority findByName(String name);
}
