package com.example.jwtspringdatabaseusers.authority.dto;

import com.example.jwtspringdatabaseusers.base.dto.BaseDTO;

import java.util.Date;

public class AuthorityDTO extends BaseDTO {

    private String name;

    public AuthorityDTO() {
    }

    public AuthorityDTO(Long id, Date techDate, String name) {
        super(id, techDate);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
