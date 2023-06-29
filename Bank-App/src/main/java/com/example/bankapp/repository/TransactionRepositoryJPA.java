package com.example.bankapp.repository;

import com.example.bankapp.model.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionRepositoryJPA implements CRUDRepositoryJPA<Transaction> {

    //needed
    @Override
    public void insert(Transaction transaction) {

        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(transaction);
            em.getTransaction().commit();
            em.close();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }

    //no use
    @Override
    public List<Transaction> readAll() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            TypedQuery<Transaction> query = em.createQuery("SELECT transaction FROM Transaction transaction", Transaction.class);
            transactions = query.getResultList();

            em.getTransaction().commit();
            em.close();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    //no use
    @Override
    public Optional<Transaction> readById(long id) {
        Transaction transaction = null;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        transaction = em.find(Transaction.class , id);

        em.getTransaction().commit();
        em.close();


        return Optional.ofNullable(transaction);
    }

    //no use
    @Override
    public void update(Transaction transaction) {
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(transaction);
            em.getTransaction().commit();
            em.close();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }

    //no use
    @Override
    public void delete(Transaction transaction) {

        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Transaction transactionInContext = em.merge(transaction);
            em.remove(transactionInContext);
            em.getTransaction().commit();
            em.close();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }

    // get List<transaction> with receiverID = x OR senderID = x (for transaction history)
    public List<Transaction> readAllTransactionsByUser(int userID){
        List<Transaction> transactions = null;
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            TypedQuery<Transaction> query = em.createQuery("select a from Transaction a where a.sender.userID = ?1 or a.receiver.userID = ?2", Transaction.class);
//          String givenUserID = Integer.toString(userID);
            transactions = query
                    .setParameter(1,userID)
                    .setParameter(2,userID)
                    .getResultList();

            em.getTransaction().commit();
            em.close();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }


        return transactions;
    }

}
