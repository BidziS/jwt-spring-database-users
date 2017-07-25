package com.example.jwtspringdatabaseusers.user.service.impl;

import com.example.jwtspringdatabaseusers.MyServerException;
import com.example.jwtspringdatabaseusers.authority.entity.Authority;
import com.example.jwtspringdatabaseusers.authority.repository.IAuthorityRepository;
import com.example.jwtspringdatabaseusers.authority.service.IAuthorityService;
import com.example.jwtspringdatabaseusers.user.dto.UserDTO;
import com.example.jwtspringdatabaseusers.user.dto.UserDTOAuthorities;
import com.example.jwtspringdatabaseusers.user.dto.UserDTOCreate;
import com.example.jwtspringdatabaseusers.user.entity.User;
import com.example.jwtspringdatabaseusers.user.repository.IUserRepository;
import com.example.jwtspringdatabaseusers.user.service.IUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService{

    private IUserRepository userRepository;
    private IAuthorityRepository authorityRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserService(IUserRepository userRepository, IAuthorityRepository authorityRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.modelMapper = modelMapper;
    }

    //CREATE
    @Override
    public UserDTO saveUser(UserDTOCreate userDTO) {
        User user = userDTO.getEmail() == null ? null : userRepository.findByEmail(userDTO.getEmail());
        if (user == null){
            user = modelMapper.map(userDTO, User.class);
            user.setActive(true);
            List<Authority> authorities = new ArrayList<>();
            authorities.add(authorityRepository.findByName("USER"));
            user.setAuthorities(authorities);
            return modelMapper.map(userRepository.save(user), UserDTO.class);
        }
        user.setTechDate(new Date());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }

    //READ
    @Override
    public List<UserDTO> getAllUsers() {
        Type usersListType = new TypeToken<List<UserDTO>>() {}.getType();
        return modelMapper.map(userRepository.findAll(), usersListType);
    }

    @Override
    public List<UserDTO> getAllUsersByAuthority(String authorityName) {
        Type usersListType = new TypeToken<List<UserDTO>>() {}.getType();
        return modelMapper.map(userRepository.findAllByAuthority(authorityName), usersListType);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return modelMapper.map(userRepository.findOne(id), UserDTO.class);
    }

    @Override
    public UserDTO getUserByEmail(String email) throws MyServerException {
        return modelMapper.map(userRepository.findByEmail(email), UserDTO.class);
    }

    //UPDATE
    @Override
    public UserDTO activeUser(Long id) throws MyServerException {
        User user = userRepository.findOne(id);
        if(user == null) throw new MyServerException("User with this id not exists", HttpStatus.NOT_FOUND);

        user.setActive(true);

        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDTO unactiveUser(Long id) throws MyServerException {
        User user = userRepository.findOne(id);
        if(user == null) throw new MyServerException("User with this id not exists", HttpStatus.NOT_FOUND);

        user.setActive(false);

        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDTO changeUserAuthorities(UserDTOAuthorities userDTOAuthorities) throws MyServerException {
        User user = userRepository.findOne(userDTOAuthorities.getId());
        if(user == null) throw new MyServerException("User with this id not exists", HttpStatus.NOT_FOUND);

        List<Authority> authorities = new ArrayList<>();

        for(String authorityName : userDTOAuthorities.getAuthorities()){
            Authority authority = authorityRepository.findByName(authorityName);
            if(authority == null) throw new MyServerException("Authority with this name not exists", HttpStatus.NOT_FOUND);

            if (authorities.contains(authority)){
                continue;
            }
            authorities.add(authority);
        }
        user.setAuthorities(authorities);

        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }


    //DELETE
}
