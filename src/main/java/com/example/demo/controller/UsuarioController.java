package com.example.demo.controller;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;

//import static com.example.demo.enums.RolesEnum.ADMIN;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

//    @RolesAllowed(ADMIN)
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuario() {
        return ResponseEntity.ok(usuarioService.getUsers());
    }

//    @RolesAllowed(ADMIN)
    @PostMapping
    public ResponseEntity<String> postUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body("");
    }

}
