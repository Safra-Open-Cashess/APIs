package com.example.demo.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRepository {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long rfID;
    private String username;
    private String password;
}
