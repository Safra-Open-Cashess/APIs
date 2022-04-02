package la.foton.wideview.authserver.service;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import la.foton.wideview.authserver.entity.AppRole;
import la.foton.wideview.authserver.entity.AppUser;

@Component
public class ServiceConfig {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(AppUserService userService, AppRoleService roleService) {
		return args -> {
			roleService.saveRole(new AppRole(null, "ROLE_USER"));
			roleService.saveRole(new AppRole(null, "ROLE_TI"));
			roleService.saveRole(new AppRole(null, "ROLE_ADMIN"));

			// one user per role
			userService.saveUser(new AppUser(null, "Fulano", "12324", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Ciclano", "12324", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Beltrano", "12324", new ArrayList<>()));

			// client-secret
			userService.saveUser(new AppUser(null, "wideview-ms", "12324", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "sonda-worker-ms", "12324", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "sonda-api-ms", "12324", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "service-registry-ms", "12324", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "gateway-ms", "12324", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "auth-server-ms", "12324", new ArrayList<>()));

			userService.addRoleToUser("Fulano", "ROLE_USER");
			userService.addRoleToUser("Ciclano", "ROLE_TI");
			userService.addRoleToUser("Beltrano", "ROLE_ADMIN");
		};
	}
}
