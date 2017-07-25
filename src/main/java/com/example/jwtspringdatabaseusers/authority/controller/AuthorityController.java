package com.example.jwtspringdatabaseusers.authority.controller;

import com.example.jwtspringdatabaseusers.MyServerException;
import com.example.jwtspringdatabaseusers.authority.dto.AuthorityDTO;
import com.example.jwtspringdatabaseusers.authority.service.IAuthorityService;
import com.example.jwtspringdatabaseusers.base.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/authority")
//@PreAuthorize("hasAnyRole('ADMIN')")
public class AuthorityController {

    @Autowired
    IAuthorityService authorityService;

    //CREATE & EDIT
    @RequestMapping(value="/save",method = RequestMethod.POST, consumes ="application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<AuthorityDTO> saveAuthority(@RequestBody AuthorityDTO authorityDTO){
        return new ResponseEntity<>(authorityService.saveAuthority(authorityDTO), HttpStatus.OK);
    }

    //READ
    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<AuthorityDTO>> getAllAuthorities(){
        return new ResponseEntity<>(authorityService.getAllAuthorities(), HttpStatus.OK);
    }

    @RequestMapping(value="/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AuthorityDTO> getAuthorityById(@PathVariable("id") Long id){
        return new ResponseEntity<>(authorityService.getAuthorityById(id), HttpStatus.OK);
    }

    @RequestMapping(value="/getByName/{name}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AuthorityDTO> getAuthorityByName(@PathVariable("name") String name) throws MyServerException {
        return new ResponseEntity<>(authorityService.getAuthorityByName(name), HttpStatus.OK);
    }

    //UPDATE

    //DELETE
    @RequestMapping(value="/removeById/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseDTO> deleteAuthor(@PathVariable("id") Long id){
        try {
            authorityService.deleteAuthority(id);
            return new ResponseEntity<>(new ResponseDTO("Deleted"), HttpStatus.OK);
        }catch (MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }

    }
}
