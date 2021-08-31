package com.example.springbootsecurityapp.security.jwt;

import com.example.springbootsecurityapp.security.UserPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Profile("jwt")
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-token-ttl}")
    private Long jwtTTL;

    @Autowired
    public JWTSecurityConfig(@Qualifier("UserDetailsPermissionServiceDb") UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtSecret, jwtTTL))
                .addFilterAfter(new JWTTokenVerifier(jwtSecret), JWTAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/h2-console", "/h2-console/**").permitAll()
                .antMatchers(HttpMethod.GET, "/students", "/students/*").hasAuthority(UserPermission.STUDENT_READ.getUserPermission())
                .antMatchers(HttpMethod.POST, "/students", "/students/*").hasAuthority(UserPermission.STUDENT_WRITE.getUserPermission())
                .antMatchers(HttpMethod.PUT, "/students", "/students/*").hasAuthority(UserPermission.STUDENT_WRITE.getUserPermission())
                .antMatchers(HttpMethod.DELETE, "/students", "/students/*").hasAuthority(UserPermission.STUDENT_WRITE.getUserPermission())
                .anyRequest()
                .authenticated();

        //needed to add following to get h2-console to show up when using spring security
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
