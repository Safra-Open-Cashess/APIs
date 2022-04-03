package com.safra.open.cashless.config;

import com.safra.open.cashless.model.Role;
import com.safra.open.cashless.model.Usuario;
import com.safra.open.cashless.repository.RoleRepository;
import com.safra.open.cashless.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

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

			usuarioService.saveUser(new Usuario(null, "ABC1234", "Fulano", "12324", new ArrayList<>(), 0.0, Collections.emptyList()));
			usuarioService.saveUser(new Usuario(null, "DEF1234", "Ciclano", "12324", new ArrayList<>(), 0.0, Collections.emptyList()));
			usuarioService.saveUser(new Usuario(null, "CD0012RFBS", "Beltrano", "12324", new ArrayList<>(), 1000.0, Collections.emptyList()));

			usuarioService.addRoleToUser("Fulano", "ROLE_USER");
			usuarioService.addRoleToUser("Ciclano", "ROLE_TI");
			usuarioService.addRoleToUser("Beltrano", "ROLE_ADMIN");
		};
	}
}
