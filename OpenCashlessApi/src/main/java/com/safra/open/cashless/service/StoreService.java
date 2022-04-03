package com.safra.open.cashless.service;

import com.safra.open.cashless.dto.UpdateStoreDTO;
import com.safra.open.cashless.model.Transaction;
import com.safra.open.cashless.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safra.open.cashless.dto.StoreDTO;
import com.safra.open.cashless.model.Store;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService
{
	@Autowired
	private StoreRepository storeRepository;
	
	public void createStore(
		final StoreDTO storeDTO)
	{
		final Store store = Store.builder()
				.cnpj(storeDTO.getCnpj())
				.nomeFantasia(storeDTO.getNomeFantasia())
				.razaoSocial(storeDTO.getRazaoSocial())
				.active(Boolean.TRUE)
				.transactions(Collections.emptyList())
				.build();
		storeRepository.save(store);
	}

	public Store getStoreByCnpj(
		final String cnpj)
	{
		return findStoreByCnpj(cnpj);
	}

	public List<Transaction> getTransactionsByStoreId(
			final Long id)
	{
		final Store store = findStoreById(id);
		return store.getTransactions();
	}

	public void updateStore(
		final String cnpj,
		final UpdateStoreDTO updatedStoreDTO)
	{
		final Store store = findStoreByCnpj(cnpj);
		final Store updatedStore = Store.builder()
				.id(store.getId())
				.cnpj(store.getCnpj())
				.nomeFantasia(updatedStoreDTO.getNomeFantasia())
				.razaoSocial(updatedStoreDTO.getRazaoSocial())
				.active(store.getActive())
				.build();
		storeRepository.save(updatedStore);
	}

	public void deleteStore(
		final Long id)
	{
		final Store recoveredStore = findStoreById(id);
		final Store store = Store.builder()
				.id(recoveredStore.getId())
				.cnpj(recoveredStore.getCnpj())
				.razaoSocial(recoveredStore.getRazaoSocial())
				.nomeFantasia(recoveredStore.getNomeFantasia())
				.active(Boolean.FALSE)
				.build();
		storeRepository.save(store);
	}

	private Store findStoreById(
		final Long id)
	{
		final Optional<Store> oStore = storeRepository.findById(id);
		return extractStore(oStore);
	}

	private Store findStoreByCnpj(
		final String cnpj)
	{
		final Optional<Store> oStore = storeRepository.findByCnpjEqualsAndActiveEquals(cnpj, Boolean.TRUE);
		return extractStore(oStore);
	}

	private Store extractStore(
		final Optional<Store> oStore)
	{
		return oStore.orElseThrow(
				() -> new RuntimeException("Store not found")
		);
	}
}