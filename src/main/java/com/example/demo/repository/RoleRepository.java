package com.example.demo.repository;

import la.foton.wideview.authserver.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByName(String name);
}