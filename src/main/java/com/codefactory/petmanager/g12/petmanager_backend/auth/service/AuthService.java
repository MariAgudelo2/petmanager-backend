package com.codefactory.petmanager.g12.petmanager_backend.auth.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codefactory.petmanager.g12.petmanager_backend.common.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public String login(String email, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );
            auth.getPrincipal();
            var role = auth.getAuthorities().stream().findFirst().map(a -> a.getAuthority()).orElseThrow(() -> new InternalError("Rol no encontrado"));
            return jwtService.generateToken(email, role);
        } catch (DisabledException ex) {
            throw new DisabledException("La cuenta del usuario está desactivada", ex);
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("El email o contraseña son inválidos", ex);
        }
    }
}


