package com.sir.wallet.services;

import com.sir.wallet.model.Transaction;

import java.util.Optional;

public interface TransactionService {

    Transaction createTransaction(Transaction transaction );

    Optional<Transaction> getTransactionById(Long id);

    Transaction updateTransaction(Transaction transaction);

    void deleteTransaction(Long id);

    Iterable<Transaction> getAllTransactions();
}
