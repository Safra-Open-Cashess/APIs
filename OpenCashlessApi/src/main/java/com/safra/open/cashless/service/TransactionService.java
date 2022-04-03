package com.safra.open.cashless.service;

import com.safra.open.cashless.dto.TransactionDTO;
import com.safra.open.cashless.enums.Status;
import com.safra.open.cashless.model.Transaction;
import com.safra.open.cashless.model.Usuario;
import com.safra.open.cashless.repository.TransactionRepository;
import com.safra.open.cashless.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
            .date(LocalDate.now())
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