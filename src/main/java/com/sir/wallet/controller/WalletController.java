package com.sir.wallet.controller;

import com.sir.wallet.model.Wallet;
import com.sir.wallet.services.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/wallets/{id}")
    public Optional<Wallet> getWallet(@PathVariable long id) {

        return walletService.getWalletById(id);
    }

    @PutMapping("/wallets/{id}")
    public ResponseEntity<Wallet> updateWallet(@PathVariable long id, @RequestBody Wallet wallet) throws Exception {

        Wallet w = walletService.updateWallet(id, wallet);
        return new ResponseEntity<Wallet>(w, HttpStatus.OK);
    }

    @PostMapping(value = "/postsWallet")
    public ResponseEntity<Wallet> saveWallet(@RequestBody  Wallet wallet) {

        Wallet w = walletService.saveWallet(wallet);
        return new ResponseEntity<Wallet>(w, HttpStatus.CREATED);
    }

    @GetMapping("/wallets/all")
    public Iterable<Wallet> getAllWallets() {
        return walletService.getAllWallets();
    }

    @DeleteMapping("/wallets/{id}")
    public ResponseEntity deleteWallet(@PathVariable("id") long id)  {
        walletService.deleteWallet(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
