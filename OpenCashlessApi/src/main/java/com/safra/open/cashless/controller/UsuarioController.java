package com.safra.open.cashless.controller;

import com.safra.open.cashless.dto.UsuarioDTO;
import com.safra.open.cashless.model.Transaction;
import com.safra.open.cashless.model.Usuario;
import com.safra.open.cashless.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;

//import static com.safra.open.cashless.enums.RolesEnum.ADMIN;

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

    @GetMapping("/{username}/transacoes")
    public ResponseEntity<List<Transaction>> getTransactionsByStoreId(
            @PathVariable(name = "username") String username)
    {
        final List<Transaction> transactions = usuarioService.getTransactionsByUserId(username);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

}
