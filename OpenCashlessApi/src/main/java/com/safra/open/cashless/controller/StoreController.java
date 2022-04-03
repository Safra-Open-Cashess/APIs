package com.safra.open.cashless.controller;

import com.safra.open.cashless.dto.UpdateStoreDTO;
import com.safra.open.cashless.model.Store;
import com.safra.open.cashless.model.Transaction;
import com.safra.open.cashless.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.safra.open.cashless.dto.StoreDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoreController
{
	@Autowired
	private StoreService storeService;

	@PostMapping("/loja")
	public ResponseEntity<Void> createStore(
		@RequestBody StoreDTO StoreDTO)
	{
		storeService.createStore(StoreDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/loja/{cnpj}")
	public ResponseEntity<Store> getStoreByCnpj(
			@PathVariable(name = "cnpj") String cnpj)
	{
		final Store loja = storeService.getStoreByCnpj(cnpj);
		return new ResponseEntity<>(loja, HttpStatus.OK);
	}

	@GetMapping("/loja/{id}/transacao")
	public ResponseEntity<List<Transaction>> getTransactionsByStoreId(
			@PathVariable(name = "id") Long id)
	{
		final List<Transaction> transactions = storeService.getTransactionsByStoreId(id);
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	@PutMapping("/loja/{cnpj}")
	public ResponseEntity<Void> updateStore(
		@PathVariable(name = "cnpj") String cnpj,
		@RequestBody UpdateStoreDTO lojaDTO)
	{
		storeService.updateStore(cnpj, lojaDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/loja/{id}")
	public ResponseEntity<Void> deleteStore(
		@PathVariable(name = "id") Long id)
	{
		storeService.deleteStore(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}