package com.safra.open.cashless.repository;

import com.safra.open.cashless.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findByUsername(String username);
    Optional<Usuario> findByRfID(String rfID);
}