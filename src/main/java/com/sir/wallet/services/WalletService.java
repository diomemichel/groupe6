package com.sir.wallet.services;

import com.sir.wallet.model.Wallet;

import java.util.Optional;

public interface WalletService {

    Optional<Wallet> getWalletById(Long id);

    Wallet saveWallet(Wallet wallet);

    Wallet updateWallet(Long id, Wallet wallet) throws Exception;

    void deleteWallet(Long id);

    Iterable<Wallet> getAllWallets();
}
