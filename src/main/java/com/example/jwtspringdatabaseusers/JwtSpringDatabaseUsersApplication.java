package com.example.jwtspringdatabaseusers;

import com.example.jwtspringdatabaseusers.authority.AuthorityEntity;
import com.example.jwtspringdatabaseusers.authority.IAuthorityRepository;
import com.example.jwtspringdatabaseusers.user.entity.UserEntity;
import com.example.jwtspringdatabaseusers.user.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JwtSpringDatabaseUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtSpringDatabaseUsersApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(IUserRepository userRepository, IAuthorityRepository authorityRepository){

		AuthorityEntity authorityUser = new AuthorityEntity("ROLE_USER");

		authorityRepository.save(authorityUser);

		AuthorityEntity authorityAdmin = new AuthorityEntity("ROLE_ADMIN");

		authorityRepository.save(authorityAdmin);

		List<AuthorityEntity> adminAuthorities = authorityRepository.findAll();

		List<AuthorityEntity> userAuthorities = new ArrayList<>();

		userAuthorities.add(authorityUser);

		UserEntity user = new UserEntity("Daniel","Cudnik", "danio@o2.pl", "123456", userAuthorities);

		userRepository.save(user);

		UserEntity admin = new UserEntity("Daniel","Cudnik", "admin@o2.pl", "123456", adminAuthorities);

		userRepository.save(admin);

		List<UserEntity> users = userRepository.findAll();




		return null;
	}
}
