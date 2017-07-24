package com.example.jwtspringdatabaseusers.user.controller;

import com.example.jwtspringdatabaseusers.user.entity.UserEntity;
import com.example.jwtspringdatabaseusers.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    IUserRepository userRepository;

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<List<UserEntity>> getAllAuthors(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/getById/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('USER')")
    @ResponseBody
    public ResponseEntity<UserEntity> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userRepository.findOne(id), HttpStatus.OK);
    }
}
