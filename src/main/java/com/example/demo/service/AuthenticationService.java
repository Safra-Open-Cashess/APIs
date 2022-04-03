package com.example.demo.service;

import com.example.demo.dto.CredentialsDTO;
import com.example.demo.dto.JwtResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtTokenService jwtTokenService;
    private final UserDetailsService userDatailsService;
    private final AuthenticationManager authenticationManager;

    public JwtResponseDTO authenticate(CredentialsDTO credentialsDTO) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentialsDTO.getUsername(), credentialsDTO.getPassword()));
        final UserDetails user = this.userDatailsService.loadUserByUsername(credentialsDTO.getUsername());
        final String accessJwtToken = this.jwtTokenService.createAccessJwtToken(user);
        final String refreshJwtToken = this.jwtTokenService.createRefreshJwtToken(user);
        return new JwtResponseDTO(accessJwtToken, refreshJwtToken);
    }
}
