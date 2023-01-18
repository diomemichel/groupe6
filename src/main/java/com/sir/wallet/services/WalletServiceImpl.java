package com.sir.wallet.services;

import com.sir.wallet.model.Wallet;
import com.sir.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public Optional<Wallet> getWalletById(Long id) {

        return walletRepository.findById(id);
    }

    @Override
    public Wallet saveWallet(Wallet wallet) {
        //walletRepository.save(wallet);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet updateWallet(Long id, Wallet wallet) {

        return null;
    }

    @Override
    public void deleteWallet(Wallet wallet) {

    }

    @Override
    public Iterable<Wallet> getAllWallets() {

        return walletRepository.findAll();
    }
}
