package com.safra.open.cashless.repository;

import com.safra.open.cashless.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}