package com.example.demo.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class UsuarioRepository {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long rfID;
    private String username;
    private String password;
}
