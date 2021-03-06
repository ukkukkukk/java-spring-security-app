package com.example.springbootsecurityapp.security.jwt;

import com.example.springbootsecurityapp.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    private final Long jwtTTL;
    private final String jwtSecret;
    private final static String BEARER_TOKEN_PREFIX = "Bearer ";
    private final static String AUTHORITIES_CLAIM_FIELD_NAME = "authorities";

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, String jwtSecret, Long jwtTTL) {
        this.authenticationManager = authenticationManager;
        this.jwtSecret = jwtSecret;
        this.jwtTTL = jwtTTL;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            return authenticationManager.authenticate(authenticate);
        } catch (IOException ex) {
            throw new RuntimeException("Error with authentication. ", ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Instant currentTime = Instant.now();

        String jwtToken = Jwts.builder()
                .setSubject(authResult.getName())
                .claim(AUTHORITIES_CLAIM_FIELD_NAME, authResult.getAuthorities())
                .setIssuedAt(Date.from(currentTime))
                .setExpiration(Date.from(currentTime.plusSeconds(jwtTTL)))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();

        response.addHeader(HttpHeaders.AUTHORIZATION, BEARER_TOKEN_PREFIX + jwtToken);
    }
}
