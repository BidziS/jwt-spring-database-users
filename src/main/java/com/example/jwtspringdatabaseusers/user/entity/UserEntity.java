package com.example.jwtspringdatabaseusers.user.entity;

import com.example.jwtspringdatabaseusers.authority.AuthorityEntity;
import com.example.jwtspringdatabaseusers.base.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@SequenceGenerator(allocationSize = 1, name = "SEQ", sequenceName = "GEN_USER_ID")
public class UserEntity extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")
    private List<AuthorityEntity> authorities;

    public UserEntity() {
    }

    public UserEntity(String firstName, String lastName, String email, String password, List<AuthorityEntity> authorities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
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

    public List<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }
}
