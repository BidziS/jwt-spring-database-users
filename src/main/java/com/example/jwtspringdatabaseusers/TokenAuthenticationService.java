package com.example.jwtspringdatabaseusers;

import com.example.jwtspringdatabaseusers.authority.AuthorityEntity;
import com.example.jwtspringdatabaseusers.user.entity.UserEntity;
import com.example.jwtspringdatabaseusers.user.repository.IUserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static java.util.Collections.emptyList;

@Component
public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    static IUserRepository userRepositoryS;


    @Autowired
    private IUserRepository userRepository;

    @PostConstruct
    public void init(){
        userRepositoryS = userRepository;
    }

    public static void addAuthentication(HttpServletResponse res, String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    public UserEntity metod(String email){
        return userRepository.findByEmail(email);
    }


    public static Authentication getAuthentication(HttpServletRequest request) {

        TokenAuthenticationService t = new TokenAuthenticationService();
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            UserEntity userFromDatabase = null;

            try{
                userFromDatabase = userRepositoryS.findByEmail(user);
            }catch (NullPointerException e){
                System.out.print(e);
            }


            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, getAuthorities(userFromDatabase.getAuthorities())) :
                    null;
        }
        return null;
    }
    private static Collection<? extends GrantedAuthority> getAuthorities(List<AuthorityEntity> authorities){
        List<GrantedAuthority> authList = new ArrayList<>();
        for (AuthorityEntity authority : authorities){
            authList.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return authList;
    }
}
