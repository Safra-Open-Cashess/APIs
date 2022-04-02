package com.example.demo.config;

import com.example.demo.model.Role;
import com.example.demo.model.Usuario;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserConfig {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UsuarioService usuarioService, RoleRepository roleRepository) {
		return args -> {
			roleRepository.save(new Role(null, "ROLE_USER"));
			roleRepository.save(new Role(null, "ROLE_TI"));
			roleRepository.save(new Role(null, "ROLE_ADMIN"));

			usuarioService.saveUser(new Usuario(null, null, "Fulano", "12324", new ArrayList<>()));
			usuarioService.saveUser(new Usuario(null, "CD0012RFBS", "Ciclano", "12324", new ArrayList<>()));
			usuarioService.saveUser(new Usuario(null, "CD0012RFBS", "Beltrano", "12324", new ArrayList<>()));

			usuarioService.addRoleToUser("Fulano", "ROLE_USER");
			usuarioService.addRoleToUser("Ciclano", "ROLE_TI");
			usuarioService.addRoleToUser("Beltrano", "ROLE_ADMIN");
		};
	}
}
