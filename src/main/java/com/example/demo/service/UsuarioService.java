package com.example.demo.service;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.model.Role;
import com.example.demo.model.Usuario;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Usuario user = usuarioRepository.findByUsername(username);
        final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        if (Objects.isNull(user)){
            throw new UsernameNotFoundException("User not found in the database");
        }else{
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
        }
    }

    public Usuario saveUser(Usuario user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usuarioRepository.save(user);
    }

    public Usuario saveUser(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario(usuarioDTO);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public void addRoleToUser(String username, String roleName) {
        final Usuario user = usuarioRepository.findByUsername(username);
        final Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    public Usuario getUser(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public List<Usuario> getUsers() {
        return usuarioRepository.findAll();
    }


}
