package com.example.bankapp.model;

import com.example.bankapp.repository.TransactionRepositoryJPA;
import com.example.bankapp.repository.UserRepositoryJPA;
import com.example.bankapp.repository.WalletRepositoryJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class LoadedData {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankAppDBjpa");
    static EntityManager em = emf.createEntityManager();
    static UserRepositoryJPA userRepository = new UserRepositoryJPA();
    static WalletRepositoryJPA walletRepository = new WalletRepositoryJPA();
    static TransactionRepositoryJPA transactionRepository = new TransactionRepositoryJPA();



    static int loginID;

    public static Optional<User> loadedUser = null;
    static List<Transaction> loadedTransactionHistoryList = null;
    static List<Wallet> loadedWallets = null;


    static User loadedReceiver;
    static Wallet selectedUserWallet;
    static Wallet selectedReceiverWallet;
    static Transaction newUserTransaction;


    public static void refreshUserData() {

        loadedUser = userRepository.readByLoginDataOnlyID(loginID);
        loadedTransactionHistoryList = transactionRepository.readAllTransactionsByUser(loginID);
        loadedWallets = walletRepository.readByUserID(loginID);

        System.out.println("User data has been updated: ");
        System.out.println(loadedUser +" "+ loadedTransactionHistoryList +" "+ loadedWallets);



    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static void setEmf(EntityManagerFactory emf) {
        LoadedData.emf = emf;
    }

    public static EntityManager getEm() {
        return em;
    }

    public static void setEm(EntityManager em) {
        LoadedData.em = em;
    }

    public static UserRepositoryJPA getUserRepository() {
        return userRepository;
    }

    public static void setUserRepository(UserRepositoryJPA userRepository) {
        LoadedData.userRepository = userRepository;
    }

    public static WalletRepositoryJPA getWalletRepository() {
        return walletRepository;
    }

    public static void setWalletRepository(WalletRepositoryJPA walletRepository) {
        LoadedData.walletRepository = walletRepository;
    }

    public static TransactionRepositoryJPA getTransactionRepository() {
        return transactionRepository;
    }

    public static void setTransactionRepository(TransactionRepositoryJPA transactionRepository) {
        LoadedData.transactionRepository = transactionRepository;
    }

    public static int getLoginID() {
        return loginID;
    }

    public static void setLoginID(int loginID) {
        LoadedData.loginID = loginID;
    }

    public static Optional<User> getLoadedUser() {
        return loadedUser;
    }

    public static void setLoadedUser(Optional<User> loadedUser) {
        LoadedData.loadedUser = loadedUser;
    }

    public static List<Transaction> getLoadedTransactionHistoryList() {
        return loadedTransactionHistoryList;
    }

    public static void setLoadedTransactionHistoryList(List<Transaction> loadedTransactionHistoryList) {
        LoadedData.loadedTransactionHistoryList = loadedTransactionHistoryList;
    }

    public static List<Wallet> getLoadedWallets() {
        return loadedWallets;
    }

    public static void setLoadedWallets(List<Wallet> loadedWallets) {
        LoadedData.loadedWallets = loadedWallets;
    }

    public static User getLoadedReceiver() {
        return loadedReceiver;
    }

    public static void setLoadedReceiver(User loadedReceiver) {
        LoadedData.loadedReceiver = loadedReceiver;
    }

    public static Wallet getSelectedUserWallet() {
        return selectedUserWallet;
    }

    public static void setSelectedUserWallet(Wallet selectedUserWallet) {
        LoadedData.selectedUserWallet = selectedUserWallet;
    }

    public static Wallet getSelectedReceiverWallet() {
        return selectedReceiverWallet;
    }

    public static void setSelectedReceiverWallet(Wallet selectedReceiverWallet) {
        LoadedData.selectedReceiverWallet = selectedReceiverWallet;
    }

    public static Transaction getNewUserTransaction() {
        return newUserTransaction;
    }

    public static void setNewUserTransaction(Transaction newUserTransaction) {
        LoadedData.newUserTransaction = newUserTransaction;
    }
}
