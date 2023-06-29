package com.example.bankapp.repository;

import com.example.bankapp.model.BankAppCurrency;
import com.example.bankapp.model.Transaction;
import com.example.bankapp.model.Wallet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WalletRepositoryJPA implements CRUDRepositoryJPA<Wallet>{

    //no use
    @Override
    public void insert(Wallet wallet) {
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(wallet);
            em.getTransaction().commit();
            em.close();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }

    //no use
    @Override
    public List<Wallet> readAll() {
        List<Wallet> wallets = new ArrayList<>();
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            TypedQuery<Wallet> query = em.createQuery("SELECT transaction FROM Transaction transaction", Wallet.class);
            wallets = query.getResultList();

            em.getTransaction().commit();
            em.close();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        return wallets;    }

    //no use
    @Override
    public Optional<Wallet> readById(long id) {
        Optional<Wallet> wallet = null;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        wallet = Optional.ofNullable(em.find(Wallet.class, id));

        em.getTransaction().commit();
        em.close();


        return wallet;    }

    //maybe
    @Override
    public void update(Wallet wallet) {

        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(wallet);
            em.getTransaction().commit();
            em.close();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }

    //no use
    @Override
    public void delete(Wallet wallet)
    {
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Wallet walletInContext = em.merge(wallet);
            em.remove(walletInContext);
            em.getTransaction().commit();
            em.close();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    //get List<wallet> with UserID (for main window)
    public List<Wallet> readByUserID(int id)
    {
        List<Wallet> wallets = new ArrayList<>();
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            TypedQuery<Wallet> query = em.createQuery("select a from Wallet a where a.user.userID = ?1", Wallet.class);
            String givenUserID = Integer.toString(id);
            query.setParameter(1,givenUserID);
            wallets = query.getResultList();

            em.getTransaction().commit();
            em.close();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }



        return wallets;

    }

    //get wallet with UserID AND currency (for transactions and currency exchange)
    public Wallet readByIDandCurrency (int userID, BankAppCurrency bankAppCurrency){
        Wallet wallet = null;
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            TypedQuery<Wallet> query = em.createQuery("select a from Wallet a where a.user.userID = ?1 and a.bankAppCurrency = ?2", Wallet.class);
            String givenUserID = Integer.toString(userID);
//          no need:
//          String givenCurrency = String.valueOf(bankAppCurrency);

            wallet = query
                    .setParameter(1,userID)
                    .setParameter(2,bankAppCurrency)
                    .getSingleResult();

            em.getTransaction().commit();
            em.close();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }


        return wallet;
    }





}
