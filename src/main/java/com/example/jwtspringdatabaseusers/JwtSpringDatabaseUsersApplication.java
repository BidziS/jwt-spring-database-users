package com.example.jwtspringdatabaseusers;

import com.example.jwtspringdatabaseusers.user.entity.UserEntity;
import com.example.jwtspringdatabaseusers.user.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JwtSpringDatabaseUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtSpringDatabaseUsersApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(IUserRepository userRepository){

		UserEntity user = new UserEntity("Daniel","Cudnik", "danio@o2.pl", "123456");

		userRepository.save(user);

		List<UserEntity> users = userRepository.findAll();

		return null;
	}
}
