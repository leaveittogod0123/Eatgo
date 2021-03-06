package com.playground.noyo0123.eatgo;

import com.playground.noyo0123.eatgo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.secret}")
    private String secret; // application.yml

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin().disable()
                .csrf().disable()
                .cors().disable()
                .headers().frameOptions().disable();
    }

    @Bean
    public JwtUtil jwtUtil() {

        return new JwtUtil(secret);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
