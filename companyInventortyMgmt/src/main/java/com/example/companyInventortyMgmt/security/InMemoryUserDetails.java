package com.example.companyInventortyMgmt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class InMemoryUserDetails {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    InMemoryUserDetails(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails member = User.builder()
                .username("alice@example.com")
                .password(passwordEncoder.encode("alice"))
                .roles("MEMBER")
                .authorities("READ_DEPARTMENT")
                .build();

        UserDetails leader = User.builder()
                .username("bob@example.com")
                .password(passwordEncoder.encode("bob"))
                .roles("LEADER")
                .authorities("READ_DEPARTMENT", "EDIT_DEPARTMENT","WRITE_DEPARTMENT")
                .build();

        UserDetails manager = User.builder()
                .username("charlie@example.com")
                .password(passwordEncoder.encode("charlie"))
                .roles("MANAGER")
                .authorities("READ_DEPARTMENT", "EDIT_DEPARTMENT", "DELETE_DEPARTMENT")
                .build();

        return new InMemoryUserDetailsManager(member, leader, manager);
    }
}