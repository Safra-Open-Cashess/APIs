package com.safra.open.cashless.controller;

import com.safra.open.cashless.dto.TransactionDTO;
import com.safra.open.cashless.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController
{
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transacao")
    public ResponseEntity<Void> createTransaction(
        @RequestBody TransactionDTO transactionDTO)
    {
        transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}