package com.example.bankapp.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Proxy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Proxy(lazy = false)
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    @Id
    @Column(name = "Transaction_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_ID")
    private User sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_ID")
    private User receiver;
    @Column(name = "dateOfTransaction")
    private LocalDate dateOfTransaction;

    @Column(name = "value")
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private BankAppCurrency bankAppCurrency;

    @Column(name = "transactionsAsSender_ORDER")
    private int orderSender;

    @Column(name = "transactionsAsReceiver_ORDER")
    private int orderReceiver;



    public Transaction(){}

    public Transaction(LocalDate dateOfTransaction, BigDecimal value, BankAppCurrency bankAppCurrency) {
        this.dateOfTransaction = dateOfTransaction;
        this.value = value;
        this.bankAppCurrency = bankAppCurrency;
    }

    public Transaction(long transactionID, User sender, User receiver, LocalDate dateOfTransaction, BigDecimal value, BankAppCurrency bankAppCurrency) {
        this.transactionID = transactionID;
        this.sender = sender;
        this.receiver = receiver;
        this.dateOfTransaction = dateOfTransaction;
        this.value = value;
        this.bankAppCurrency = bankAppCurrency;
    }

    public Transaction(User sender, User receiver, LocalDate dateOfTransaction, BigDecimal value, BankAppCurrency bankAppCurrency) {
        this.sender = sender;
        this.receiver = receiver;
        this.dateOfTransaction = dateOfTransaction;
        this.value = value;
        this.bankAppCurrency = bankAppCurrency;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
//                ", sender=" + sender +
//                ", receiver=" + receiver +
                ", dateOfTransaction=" + dateOfTransaction +
                ", value=" + value +
                ", currency=" + bankAppCurrency +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionID == that.transactionID && Objects.equals(sender, that.sender) && Objects.equals(receiver, that.receiver) && Objects.equals(dateOfTransaction, that.dateOfTransaction) && Objects.equals(value, that.value) && bankAppCurrency == that.bankAppCurrency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionID, sender, receiver, dateOfTransaction, value, bankAppCurrency);
    }


    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public LocalDate getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(LocalDate dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BankAppCurrency getCurrency() {
        return bankAppCurrency;
    }

    public void setCurrency(BankAppCurrency bankAppCurrency) {
        this.bankAppCurrency = bankAppCurrency;
    }
}
