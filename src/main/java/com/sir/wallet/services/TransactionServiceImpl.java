package com.sir.wallet.services;

import com.sir.wallet.model.Transaction;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {

        Transaction w = transactionRepository.save(transaction);

        return w;
    }

    @Override
    public Optional<Transaction> getTransactionById(Long id) {

        return transactionRepository.findById(id);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {

        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Iterable<Transaction> getAllTransactions() {

        return transactionRepository.findAll();
    }
}
