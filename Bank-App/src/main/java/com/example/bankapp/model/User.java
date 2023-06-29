package com.example.bankapp.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Proxy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Proxy(lazy = false)
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @Column(name = "User_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;
    @Column(name = "password")
    private String password;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @OrderColumn(name = "wallets_ORDER")
    private List<Wallet> wallets;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sender")
    @OrderColumn(name = "transactionsAsReceiver_ORDER")
    private List<Transaction> transactionsAsSender;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receiver")
    @OrderColumn(name = "transactionsAsSender_ORDER")
    private List<Transaction> transactionsAsReceiver;




    public User(){}

    public User(String firstName, String lastName, String password, String hash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(int userID, String firstName, String lastName, String password, String hash) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(int userID, String firstName, String lastName, String password, String hash, ArrayList<Wallet> wallets, ArrayList<Transaction> transactionsAsSender, ArrayList<Transaction> transactionsAsReceiver) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.wallets = wallets;
        this.transactionsAsSender = transactionsAsSender;
        this.transactionsAsReceiver = transactionsAsReceiver;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
//                ", wallets=" + wallets +
//                ", transactionsAsSender=" + transactionsAsSender +
//                ", transactionsAsReceiver=" + transactionsAsReceiver +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(password, user.password) && Objects.equals(wallets, user.wallets) && Objects.equals(transactionsAsSender, user.transactionsAsSender) && Objects.equals(transactionsAsReceiver, user.transactionsAsReceiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, firstName, lastName, password, wallets, transactionsAsSender, transactionsAsReceiver);
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Optional<String> getFirstNameOptional() {
        return Optional.ofNullable(firstName);
    }

    public String getFirstName(){return firstName;}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Optional<String> getLastNameOptional() {
        return Optional.ofNullable(lastName);
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Optional<String> getOptionalPassword() {
        return Optional.ofNullable(password);
    }

    public String getPassword(){return password;}

    public void setPassword(String password) {
        this.password = password;
    }

    public Optional<List<Wallet>> getWallets() {
        return Optional.ofNullable(wallets);
    }

    public void setWallets(ArrayList<Wallet> wallets) {
        this.wallets = wallets;
    }

    public Optional<List<Transaction>> getTransactionsAsSender() {
        return Optional.ofNullable(transactionsAsSender);
    }

    public void setTransactionsAsSender(ArrayList<Transaction> transactionsAsSender) {
        this.transactionsAsSender = transactionsAsSender;
    }

    public Optional<List<Transaction>> getTransactionsAsReceiver() {
        return Optional.ofNullable(transactionsAsReceiver);
    }

    public void setTransactionsAsReceiver(ArrayList<Transaction> transactionsAsReceiver) {
        this.transactionsAsReceiver = transactionsAsReceiver;
    }


}
