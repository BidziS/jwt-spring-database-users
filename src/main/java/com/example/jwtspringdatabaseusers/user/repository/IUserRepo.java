package com.example.jwtspringdatabaseusers.user.repository;

import com.example.jwtspringdatabaseusers.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bidzis on 7/24/2017.
 */
public interface IUserRepo extends JpaRepository<UserEntity, Long> {

}
