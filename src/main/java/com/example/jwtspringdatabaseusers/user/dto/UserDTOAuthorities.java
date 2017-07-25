package com.example.jwtspringdatabaseusers.user.dto;

import com.example.jwtspringdatabaseusers.authority.dto.AuthorityDTO;

import java.io.Serializable;
import java.util.List;

public class UserDTOAuthorities implements Serializable{

    private Long id;
    private List<String> authorities;

    public UserDTOAuthorities() {
    }

    public UserDTOAuthorities(Long id, List<String> authorities) {
        this.id = id;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
