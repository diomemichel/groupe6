package com.sir.wallet.controller;

import com.sir.wallet.model.Transaction;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.services.TransactionService;
import com.sir.wallet.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @Autowired
    private WalletService walletService;
    @PostMapping("/transactions/{id}")
    public Transaction createTransaction(@PathVariable long id, @RequestBody Transaction transaction) {
        System.out.println(transaction.getWallet().getId());
        Wallet wa = transaction.getWallet();
        if(transaction.getType().equals("deposit")){
            wa.setBalance(wa.getBalance()+transaction.getAmount());
        }else  if(transaction.getType().equals("withdraw")){
            wa.setBalance(wa.getBalance()-transaction.getAmount());
        }else  if(transaction.getType().equals("transfer")){
            wa.setBalance(wa.getBalance()+transaction.getAmount());

            Optional<Wallet> wa2 = walletService.getWalletById(id);
            wa2.get().setBalance(wa2.get().getBalance()-transaction.getAmount());
            walletService.saveWallet(wa2.get());

        }
            //wa.setBalance(wa.getBalance()+transaction.getAmount());

        walletService.saveWallet(wa);


        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/transactions")
    public Iterable<Transaction> getAllTransactions() {

        return transactionService.getAllTransactions();
    }

    @GetMapping("/transactions/{id}")
    public Optional<Transaction> getTransaction(@PathVariable long id) {

        return transactionService.getTransactionById(id);
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity deleteTransaction(@PathVariable("id") long id)  {
        transactionService.deleteTransaction(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
