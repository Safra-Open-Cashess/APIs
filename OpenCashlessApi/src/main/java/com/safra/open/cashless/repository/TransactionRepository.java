package com.safra.open.cashless.repository;

import com.safra.open.cashless.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository
        extends
            JpaRepository<Transaction, Long>
{
}