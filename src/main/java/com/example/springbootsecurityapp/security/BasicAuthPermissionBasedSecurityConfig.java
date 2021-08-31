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
public class BasicAuthPermissionBasedSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BasicAuthPermissionBasedSecurityConfig(@Qualifier("UserDetailsPermissionServiceDb") UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console", "/h2-console/**").permitAll()
                .antMatchers(HttpMethod.GET, "/students", "/students/*").hasAuthority(UserPermission.STUDENT_READ.getUserPermission())
                .antMatchers(HttpMethod.POST, "/students", "/students/*").hasAuthority(UserPermission.STUDENT_WRITE.getUserPermission())
                .antMatchers(HttpMethod.PUT, "/students", "/students/*").hasAuthority(UserPermission.STUDENT_WRITE.getUserPermission())
                .antMatchers(HttpMethod.DELETE, "/students", "/students/*").hasAuthority(UserPermission.STUDENT_WRITE.getUserPermission())
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
