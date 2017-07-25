package com.example.jwtspringdatabaseusers.security;

import com.example.jwtspringdatabaseusers.authority.entity.Authority;
import com.example.jwtspringdatabaseusers.user.entity.User;
import com.example.jwtspringdatabaseusers.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Bidzis on 7/24/2017.
 */
@Component
public class AuthenticationRepository {

    private static IUserRepository userRepositoryS;

    @Autowired
    private IUserRepository userRepository;

    @PostConstruct
    public void init(){
        userRepositoryS = userRepository;
    }

    public static Collection<? extends GrantedAuthority> getAuthorities(String email){
        User user = userRepositoryS.findByEmail(email);

        List<Authority> authorities = user.getAuthorities();

        List<GrantedAuthority> authList = new ArrayList<>();
        for (Authority authority : authorities){
            authList.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return authList;
    }
}
