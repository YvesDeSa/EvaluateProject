package com.evaluate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MyWebSecurity extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private ClientDetailsService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/apirest/**")
            .and()
            .authorizeRequests()
                .antMatchers("/apirest/**").hasRole("ADMIN")
                .antMatchers("/clients/mydatas/**").hasAnyRole("ADMIN", "CLIENT")
                .antMatchers("/clients").hasRole("ADMIN")
                .antMatchers("/clients/**").hasRole("ADMIN")
                .antMatchers("/**").hasAnyRole("ADMIN", "CLIENT")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .usernameParameter("login");
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder authen) throws Exception{
        authen.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }
}
