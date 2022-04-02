package com.safra_open_cashless.api.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class PurchasesREST {

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> registerPurchase() {
        return ResponseEntity.ok("");
    }

}
