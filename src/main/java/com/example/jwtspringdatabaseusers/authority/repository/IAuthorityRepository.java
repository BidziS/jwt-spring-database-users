package com.example.jwtspringdatabaseusers.authority.repository;

import com.example.jwtspringdatabaseusers.authority.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bidzis on 7/24/2017.
 */
public interface IAuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
}
