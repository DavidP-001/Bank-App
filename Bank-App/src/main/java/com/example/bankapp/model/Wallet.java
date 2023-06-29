package com.example.bankapp.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Proxy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Proxy(lazy = false)
@Entity
@Table(name = "wallet")
public class Wallet implements Serializable {

    @Id
    @Column(name = "Wallet_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int walletID;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="User_ID")
    private User user;
    @Column(name = "value")
    private BigDecimal walletValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private BankAppCurrency bankAppCurrency;

    public Wallet(){}

    public Wallet(BigDecimal walletValue, BankAppCurrency bankAppCurrency) {
        this.walletValue = walletValue;
        this.bankAppCurrency = bankAppCurrency;
    }

    public Wallet(int walletID, User user, BigDecimal walletValue, BankAppCurrency bankAppCurrency) {
        this.walletID = walletID;
        this.user = user;
        this.walletValue = walletValue;
        this.bankAppCurrency = bankAppCurrency;
    }



    @Override
    public String toString() {
        return "Wallet{" +
                "walletID=" + walletID +
//                ", user=" + user +
                ", walletValue=" + walletValue +
                ", currency=" + bankAppCurrency +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return walletID == wallet.walletID && Objects.equals(user, wallet.user) && Objects.equals(walletValue, wallet.walletValue) && bankAppCurrency == wallet.bankAppCurrency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(walletID, user, walletValue, bankAppCurrency);
    }

    public int getWalletID() {
        return walletID;
    }

    public void setWalletID(int walletID) {
        this.walletID = walletID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getWalletValue() {
        return walletValue;
    }

    public void setWalletValue(BigDecimal walletValue) {
        this.walletValue = walletValue;
    }

    public BankAppCurrency getCurrency() {
        return bankAppCurrency;
    }

    public void setCurrency(BankAppCurrency bankAppCurrency) {
        this.bankAppCurrency = bankAppCurrency;
    }
}
