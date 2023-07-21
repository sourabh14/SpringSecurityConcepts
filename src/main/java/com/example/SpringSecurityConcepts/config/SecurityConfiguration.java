package com.example.SpringSecurityConcepts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
        In earlier version of spring-security, we used to extend WebSecurityConfigurerAdapter and override
        its methods to change the configuration. The newer version encourages component based security
        configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Make endpoint
        //      - /public/** as public - can be accessed without authentication
        //      - /admin/** - only user with ADMIN role can access
        //      - rest endpoints - any authenticated user can access
        // Spring will automatically add the prefix ROLE_ when checking with hasAnyRole, whereas hasAnyAuthority will not.
        http
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers("/public/**").permitAll()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .userDetailsService(userDetailsService)
                .formLogin();

        return http.build();
    }

}
