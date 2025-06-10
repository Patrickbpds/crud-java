package com.patrick.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("usuario")
                .password("$2a$10$azEdgkrLXvoVskwQwCzvwuNMkXKeuOmdFjpssIqf3DD1fiPXjVJqW")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
