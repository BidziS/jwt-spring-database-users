package com.example.jwtspringdatabaseusers.authority.entity;

import com.example.jwtspringdatabaseusers.base.entity.BaseEntity;
import com.example.jwtspringdatabaseusers.user.entity.UserEntity;

import javax.persistence.*;


/**
 * Created by Bidzis on 7/24/2017.
 */
@Entity
@Table(name = "authorities")
@SequenceGenerator(allocationSize = 1, name = "SEQ", sequenceName = "GEN_AUTHORITY_ID")
public class AuthorityEntity extends BaseEntity {

    private String name;

    public AuthorityEntity() {
    }

    public AuthorityEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
