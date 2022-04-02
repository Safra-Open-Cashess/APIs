package la.foton.wideview.authserver.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import la.foton.wideview.authserver.entity.AppRole;
import la.foton.wideview.authserver.entity.AppUser;
import la.foton.wideview.authserver.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AppUserServiceImpl implements AppUserService, UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final AppRoleService appRoleService;
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final AppUser user = appUserRepository.findByUsername(username);
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

    @Override
    public AppUser saveUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.appUserRepository.save(user);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        final AppUser user = this.appUserRepository.findByUsername(username);
        final AppRole role = this.appRoleService.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String username) {
        return this.appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        return this.appUserRepository.findAll();
    }


}
