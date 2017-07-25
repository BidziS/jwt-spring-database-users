package com.example.jwtspringdatabaseusers;

import com.example.jwtspringdatabaseusers.authority.entity.AuthorityEntity;
import com.example.jwtspringdatabaseusers.authority.repository.IAuthorityRepository;
import com.example.jwtspringdatabaseusers.user.entity.UserEntity;
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

		AuthorityEntity authorityUser = new AuthorityEntity("ROLE_USER");
		authorityRepository.save(authorityUser);

		AuthorityEntity authorityAdmin = new AuthorityEntity("ROLE_ADMIN");
		authorityRepository.save(authorityAdmin);

		List<AuthorityEntity> adminAuthorities = authorityRepository.findAll();

		List<AuthorityEntity> userAuthorities = new ArrayList<>();
		userAuthorities.add(authorityUser);

		UserEntity user = new UserEntity("Daniel","Cudnik", "danio@o2.pl", "123456", true, userAuthorities);
		userRepository.save(user);

		UserEntity admin = new UserEntity("Daniel","Cudnik", "admin@o2.pl", "123456", true,adminAuthorities);
		userRepository.save(admin);

		List<UserEntity> users = userRepository.findAll();

		return null;
	}
}
