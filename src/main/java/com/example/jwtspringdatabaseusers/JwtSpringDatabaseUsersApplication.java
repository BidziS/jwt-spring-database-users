package com.example.jwtspringdatabaseusers;

import com.example.jwtspringdatabaseusers.authority.entity.Authority;
import com.example.jwtspringdatabaseusers.authority.repository.IAuthorityRepository;
import com.example.jwtspringdatabaseusers.user.entity.User;
import com.example.jwtspringdatabaseusers.user.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JwtSpringDatabaseUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtSpringDatabaseUsersApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}

	@Bean
	public CommandLineRunner init(IUserRepository userRepository, IAuthorityRepository authorityRepository){

		Authority authorityUser = new Authority("ROLE_USER");
		authorityRepository.save(authorityUser);

		Authority authorityAdmin = new Authority("ROLE_ADMIN");
		authorityRepository.save(authorityAdmin);

		List<Authority> adminAuthorities = authorityRepository.findAll();

		List<Authority> userAuthorities = new ArrayList<>();
		userAuthorities.add(authorityUser);

		User user = new User("Daniel","Cudnik", "danio@o2.pl", "123456", true, userAuthorities);
		userRepository.save(user);

		User admin = new User("Daniel","Cudnik", "admin@o2.pl", "123456", true,adminAuthorities);
		userRepository.save(admin);

		List<User> users = userRepository.findAll();

		return null;
	}
}
