package com.safra.open.cashless.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.safra.open.cashless.model.Store;

import java.util.Optional;

@Repository
public interface StoreRepository
	extends JpaRepository<Store, Long>
{
	Optional<Store> findByCnpjEqualsAndActiveEquals(String cnpj, Boolean active);
}