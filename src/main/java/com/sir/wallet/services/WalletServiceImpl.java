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
        Wallet w;

            w = walletRepository.save(wallet);

        return w;
    }

    @Override
    public Wallet updateWallet(Long id, Wallet wallet) throws Exception {
        if (!walletRepository.existsById(id)) {
            throw new Exception("n'existe pas dans la base de données");
        }
        Wallet w = walletRepository.getById(id);//ancienne solde
        w.setBalance(wallet.getBalance() + w.getBalance());//nouvelle solde

        return walletRepository.save(w);
    }

    @Override
    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }

    @Override
    public Iterable<Wallet> getAllWallets() {

        return walletRepository.findAll();
    }
}
