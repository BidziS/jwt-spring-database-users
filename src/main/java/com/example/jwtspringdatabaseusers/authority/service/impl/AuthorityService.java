package com.example.jwtspringdatabaseusers.authority.service.impl;


import com.example.jwtspringdatabaseusers.MyServerException;
import com.example.jwtspringdatabaseusers.authority.dto.AuthorityDTO;
import com.example.jwtspringdatabaseusers.authority.entity.Authority;
import com.example.jwtspringdatabaseusers.authority.repository.IAuthorityRepository;
import com.example.jwtspringdatabaseusers.authority.service.IAuthorityService;
import com.example.jwtspringdatabaseusers.user.dto.UserDTO;
import com.example.jwtspringdatabaseusers.user.entity.User;
import com.example.jwtspringdatabaseusers.user.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AuthorityService implements IAuthorityService{

    private IAuthorityRepository authorityRepository;
    private IUserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AuthorityService(IAuthorityRepository authorityRepository, IUserRepository userRepository, ModelMapper modelMapper) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    //CREATE
    @Override
    public AuthorityDTO saveAuthority(AuthorityDTO authorityDTO) {
        Authority authority = authorityDTO.getId() == null ? null : authorityRepository.findOne(authorityDTO.getId());
        if (authority == null){
            authority = modelMapper.map(authorityDTO, Authority.class);
            return modelMapper.map(authorityRepository.save(authority), AuthorityDTO.class);
        }
        authority.setTechDate(new Date());
        authority.setName(authorityDTO.getName());
        return modelMapper.map(authorityRepository.save(authority), AuthorityDTO.class);
    }

    //READ
    @Override
    public List<AuthorityDTO> getAllAuthorities() {
        Type authoritiesListType = new TypeToken<List<AuthorityDTO>>() {}.getType();
        return modelMapper.map(authorityRepository.findAll(), authoritiesListType);
    }

    @Override
    public AuthorityDTO getAuthorityById(Long id) {
        return modelMapper.map(authorityRepository.findOne(id), AuthorityDTO.class);
    }

    @Override
    public AuthorityDTO getAuthorityByName(String name) throws MyServerException {
        return modelMapper.map(authorityRepository.findByName(name), AuthorityDTO.class);
    }

    //UPDATE

    //DELETE
    @Override
    public void deleteAuthority(Long id) throws MyServerException {
        Authority authority = authorityRepository.findOne(id);
        if(authority == null) throw new MyServerException("Author with this id not exists", HttpStatus.NOT_FOUND);

        List<User> usersWithSelectedAuthority = userRepository.findAllByAuthority(authority.getName());

        if(usersWithSelectedAuthority.size() != 0){
            throw new MyServerException("You can not delete this authority because some users have that authority!", HttpStatus.NOT_ACCEPTABLE);
        }
        authorityRepository.delete(id);
    }
}
