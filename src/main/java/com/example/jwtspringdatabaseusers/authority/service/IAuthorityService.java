package com.example.jwtspringdatabaseusers.authority.service;

import com.example.jwtspringdatabaseusers.MyServerException;
import com.example.jwtspringdatabaseusers.authority.dto.AuthorityDTO;

import java.util.List;

public interface IAuthorityService {

    //CREATE & EDIT
    AuthorityDTO saveAuthority(AuthorityDTO authorityDTO);

    //READ
    List<AuthorityDTO> getAllAuthorities();
    AuthorityDTO getAuthorityById(Long id);
    AuthorityDTO getAuthorityByName(String name) throws MyServerException;

    //UPDATE

    //DELETE
    void deleteAuthority(Long id) throws MyServerException;

}
