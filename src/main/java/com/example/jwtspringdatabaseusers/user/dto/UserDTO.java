package com.example.jwtspringdatabaseusers.user.dto;

import com.example.jwtspringdatabaseusers.authority.dto.AuthorityDTO;
import com.example.jwtspringdatabaseusers.base.dto.BaseDTO;

import java.util.Date;
import java.util.List;


public class UserDTO extends BaseDTO{

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private boolean isActive;

    private List<AuthorityDTO> authorities;

    public UserDTO() {
    }

    public UserDTO(Long id, Date techDate, String firstName, String lastName, String email, String password) {
        super(id, techDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
