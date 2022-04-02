package com.example.demo.controller;

import com.example.demo.dto.UpdateStoreDTO;
import com.example.demo.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.StoreDTO;
import com.example.demo.service.StoreService;

@RestController
@RequestMapping("/api/v1")
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