package com.example.jwtspringdatabaseusers.user.service;

import com.example.jwtspringdatabaseusers.MyServerException;
import com.example.jwtspringdatabaseusers.user.dto.UserDTO;
import com.example.jwtspringdatabaseusers.user.dto.UserDTOAuthorities;
import com.example.jwtspringdatabaseusers.user.dto.UserDTOCreate;

import java.util.List;

public interface IUserService {

    //CREATE & EDIT
    UserDTO saveUser(UserDTOCreate userDTO);

    //READ
    List<UserDTO> getAllUsers();
    List<UserDTO> getAllUsersByAuthority(String authorityName);
    UserDTO getUserById(Long id);
    UserDTO getUserByEmail(String email) throws MyServerException;

    //UPDATE


    UserDTO activeUser(Long id) throws MyServerException;
    UserDTO unactiveUser(Long id) throws MyServerException;
    UserDTO changeUserAuthorities(UserDTOAuthorities userDTOAuthorities) throws MyServerException;



    //DELETE

}
