package com.example.demo.service;

import com.example.demo.dto.TransactionDTO;
import com.example.demo.enums.Status;
import com.example.demo.model.Transaction;
import com.example.demo.model.Usuario;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService
{
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void createTransaction(
        final TransactionDTO transactionDTO)
    {
        final Optional<Usuario> oUsuario = usuarioRepository.findByRfID(
                transactionDTO.getClientRfid());
        final Usuario user = getUser(oUsuario);
        final Double amount = transactionDTO.getAmount();
        final Boolean possibleTransaction = isPossibleTransaction(user.getBalance(), amount);
        final Status status = getTransactionStatus(possibleTransaction);
        if ( status == Status.APPROVED) {
            updateUserBalance(user, amount);
        }
        final Transaction transaction = Transaction.builder()
            .storeId(transactionDTO.getStoreId())
            .clientRfid(transactionDTO.getClientRfid())
            .amount(amount)
            .transactionStatus(status)
            .userId(user.getId())
            .build();
        transactionRepository.save(transaction);
    }

    private Usuario getUser(
        final Optional<Usuario> oUser)
    {
        return oUser.orElseThrow(
            () -> new RuntimeException("User not found"));
    }

    private Usuario updateUserBalance(
        final Usuario user,
        final Double amount)
    {
        user.setBalance(user.getBalance()+amount);
        return usuarioRepository.save(user);
    }

    private Boolean isPossibleTransaction(
        final Double userBalance,
        final Double amount)
    {
        return userBalance + amount > 0;
    }

    private Status getTransactionStatus(
        final Boolean possibleTransaction)
    {
        return possibleTransaction ? Status.APPROVED : Status.REJECTED;
    }
}