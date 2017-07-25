package com.example.jwtspringdatabaseusers;

import com.example.jwtspringdatabaseusers.security.JWTAuthenticationFilter;
import com.example.jwtspringdatabaseusers.security.JWTLoginFilter;
import com.example.jwtspringdatabaseusers.user.entity.User;
import com.example.jwtspringdatabaseusers.user.repository.IUserRepository;
import com.example.jwtspringdatabaseusers.security.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IUserRepository userRepository;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*FULLY AUTHENTICATED*/
//        http.csrf().disable().authorizeRequests()
////                .antMatchers("/").permitAll()
////                .antMatchers("/swagger-ui.html/*").permitAll()
////                .antMatchers(HttpMethod.POST, "/login").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                // We filter the api/login requests
////                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
////                        UsernamePasswordAuthenticationFilter.class)
////                // And filter other requests to check the presence of JWT in header
////                .addFilterBefore(new JWTAuthenticationFilter(),
////                        UsernamePasswordAuthenticationFilter.class);

        /*PERMIT ALL REQUESTS*/
        http.authorizeRequests()
        .anyRequest().permitAll().and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Create a default account
        auth.userDetailsService(userDetailsService())
                .and()
                .inMemoryAuthentication()
                .withUser("admin")
                .password("password")
                .roles("USER");
    }
    @Bean
    protected UserDetailsService userDetailsService() {
        return new UserDetailsService(){

            @Override
            public UserDetails loadUserByUsername(String email)
                    throws UsernameNotFoundException {
                User user = userRepository.findByEmail(email);
                if(user!= null){
                    return new org.springframework.security.core.userdetails.User(user.getEmail(),
                            user.getPassword(),
                            true,true,true,true,
                            AuthenticationRepository.getAuthorities(user.getEmail()));

                }
                else {
                    throw new  UsernameNotFoundException("Do not find a user '"
                            + email + "'");
                }

            }
        };

    }

}