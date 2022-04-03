package com.example.demo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class JwtResponseDTO {
    private String jwtAccessToken;
    private String jwtRefreshToken;
}
