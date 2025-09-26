package com.codefactory.petmanager.g12.petmanager_backend.user.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codefactory.petmanager.g12.petmanager_backend.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var domainUser = userRepository.findByEmail(username)  // Dependiendo del tipo de autenticacion que se quiera hacer, en este caso email
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var authorities = Collections.singletonList(domainUser.getRole().asGrantedAuthority());
        return new User(domainUser.getUsername(), domainUser.getPassword(), authorities);
    }
}


