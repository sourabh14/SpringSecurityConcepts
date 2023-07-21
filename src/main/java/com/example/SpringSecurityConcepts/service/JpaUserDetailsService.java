package com.example.SpringSecurityConcepts.service;

import java.util.Optional;

import com.example.SpringSecurityConcepts.entity.SecurityUser;
import com.example.SpringSecurityConcepts.entity.User;
import com.example.SpringSecurityConcepts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("JpaUserDetailsService.loadUserByUsername : " + username);
        return userRepository.findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }

}
