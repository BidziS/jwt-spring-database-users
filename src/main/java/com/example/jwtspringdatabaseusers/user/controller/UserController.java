package com.example.jwtspringdatabaseusers.user.controller;

import com.example.jwtspringdatabaseusers.MyServerException;
import com.example.jwtspringdatabaseusers.base.dto.ResponseDTO;
import com.example.jwtspringdatabaseusers.user.dto.UserDTO;
import com.example.jwtspringdatabaseusers.user.dto.UserDTOAuthorities;
import com.example.jwtspringdatabaseusers.user.dto.UserDTOCreate;
import com.example.jwtspringdatabaseusers.user.entity.User;
import com.example.jwtspringdatabaseusers.user.repository.IUserRepository;
import com.example.jwtspringdatabaseusers.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
//@PreAuthorize("hasAnyRole('ADMIN')")
public class UserController {

    @Autowired
    IUserService userService;

    //CREATE & EDIT
    @RequestMapping(value="/save",method = RequestMethod.POST, consumes ="application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTOCreate user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    //READ
    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value="/getAllByAuthorityName/{authorityName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UserDTO>> getUsersByAuthorities(@PathVariable("authorityName") String authorityName) throws MyServerException {
        return new ResponseEntity<>(userService.getAllUsersByAuthority(authorityName), HttpStatus.OK);
    }

    @RequestMapping(value="/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @RequestMapping(value="/getByEmail/{email}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable("email") String email) throws MyServerException {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    //UPDATE
    @RequestMapping(value="/activeById/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<ResponseDTO> activeUser(@PathVariable("id") Long id){
        try {
            userService.activeUser(id);
            return new ResponseEntity<>(new ResponseDTO("Activated!"), HttpStatus.OK);
        }catch (MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }

    @RequestMapping(value="/unactiveById/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<ResponseDTO> unactiveUser(@PathVariable("id") Long id){
        try {
            userService.unactiveUser(id);
            return new ResponseEntity<>(new ResponseDTO("Unactivated!"), HttpStatus.OK);
        }catch (MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }

    @RequestMapping(value="/changeUserAuthorities", method = RequestMethod.PUT, consumes ="application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<UserDTO> changeUserAuthorities(@RequestBody UserDTOAuthorities user){
        try {
            return new ResponseEntity<>(userService.changeUserAuthorities(user), HttpStatus.OK);
        }catch (MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }
    //DELETE

}
