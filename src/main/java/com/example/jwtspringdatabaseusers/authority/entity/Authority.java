package com.example.jwtspringdatabaseusers.authority.entity;

import com.example.jwtspringdatabaseusers.base.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Created by Bidzis on 7/24/2017.
 */
@Entity
@Table(name = "authorities")
@SequenceGenerator(allocationSize = 1, name = "SEQ", sequenceName = "GEN_AUTHORITY_ID")
public class Authority extends BaseEntity {

    @NotNull
    private String name;

    public Authority() {
    }

    public Authority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
