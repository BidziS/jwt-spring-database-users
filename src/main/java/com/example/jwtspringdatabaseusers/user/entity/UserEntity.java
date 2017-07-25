package com.example.jwtspringdatabaseusers.user.entity;

import com.example.jwtspringdatabaseusers.authority.entity.AuthorityEntity;
import com.example.jwtspringdatabaseusers.base.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
@SequenceGenerator(allocationSize = 1, name = "SEQ", sequenceName = "GEN_USER_ID")
public class UserEntity extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")
    private List<AuthorityEntity> authorities;

    public UserEntity() {
    }

    public UserEntity(String firstName, String lastName, String email, String password, boolean isActive, List<AuthorityEntity> authorities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }
}
