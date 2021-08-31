/*
package com.example.springbootsecurityapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class BasicAuthRoleBasedSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BasicAuthRoleBasedSecurityConfig(@Qualifier("UserDetailsServiceDb") UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console", "/h2-console/**").permitAll()
                .antMatchers(HttpMethod.GET, "/students", "/students/*").hasAnyRole(UserRole.STUDENT.name(), UserRole.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/students", "/students/*").hasRole(UserRole.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/students", "/students/*").hasRole(UserRole.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/students", "/students/*").hasRole(UserRole.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        //needed to add following to get h2-console to show up when using spring security
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
*/
