package com.safra.open.cashless.controller;

import com.safra.open.cashless.dto.UpdateStoreDTO;
import com.safra.open.cashless.model.Store;
import com.safra.open.cashless.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.safra.open.cashless.dto.StoreDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class StoreController
{
	@Autowired
	private StoreService lojaService;

	@PostMapping("/loja")
	public ResponseEntity<Void> createStore(
		@RequestBody StoreDTO StoreDTO)
	{
		lojaService.createStore(StoreDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/loja/{cnpj}")
	public ResponseEntity<Store> getStoreByCnpj(
			@PathVariable(name = "cnpj") String cnpj)
	{
		Store loja = lojaService.getStoreByCnpj(cnpj);
		return new ResponseEntity<>(loja, HttpStatus.OK);
	}

	@PutMapping("/loja/{cnpj}")
	public ResponseEntity<Void> updateStore(
		@PathVariable(name = "cnpj") String cnpj,
		@RequestBody UpdateStoreDTO lojaDTO)
	{
		lojaService.updateStore(cnpj, lojaDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/loja/{id}")
	public ResponseEntity<Void> deleteStore(
		@PathVariable(name = "id") Long id)
	{
		lojaService.deleteStore(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}