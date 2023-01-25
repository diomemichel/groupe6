package com.sir.wallet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Wallet wallet;







    private long amount;
    private String type;

    public long getId() {
        return id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public long getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Constructors, getters, and setters
}
