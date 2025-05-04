package com.example.companyInventortyMgmt.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/api/v1/public/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/departments").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET,"/departments/{id}").hasAuthority("READ_DEPARTMENT")
                        .requestMatchers(HttpMethod.PUT,"/departments").hasAuthority("EDIT_DEPARTMENT")
                        .requestMatchers(HttpMethod.POST,"/departments").hasAuthority("WRITE_DEPARTMENT")
                        .requestMatchers(HttpMethod.DELETE,"/departments").hasAuthority("DELETE_DEPARTMENT")
                        .anyRequest().authenticated()
                )
                .httpBasic(org.springframework.security.config.Customizer.withDefaults())
                .userDetailsService(userDetailsService)
                .csrf((csrf) -> csrf.disable());
        return http.build();
    }
}
