package com.example.demo.controller;

import com.example.demo.dto.CredentialsDTO;
import com.example.demo.dto.JwtResponseDTO;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class JwtAuthController {
    private final JwtTokenService jwtTokenService;
    private final UserDetailsService userDatailsService;
    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponseDTO> authenticate(@RequestBody CredentialsDTO credentialsDTO) {
        return ResponseEntity.ok(authenticationService.authenticate(credentialsDTO));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponseDTO> refreshToken(@RequestBody String refreshToken) {
        final UserDetails user = this.userDatailsService.loadUserByUsername(this.jwtTokenService.getUsernameFromToken(refreshToken));
        final String renewedAccessToken = this.jwtTokenService.createAccessJwtToken(user);
        return ResponseEntity.ok(new JwtResponseDTO(renewedAccessToken,refreshToken));
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validaToken() {
        return ResponseEntity.ok("ok");
    }
}
