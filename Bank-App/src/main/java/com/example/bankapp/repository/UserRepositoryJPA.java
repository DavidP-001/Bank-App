package com.example.bankapp.repository;

import com.example.bankapp.model.Transaction;
import com.example.bankapp.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.bankapp.repository.CRUDRepositoryJPA.emf;

public class UserRepositoryJPA implements CRUDRepositoryJPA<User>{


    //no  use
    @Override
    public void insert(User user) {

        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            em.close();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }

    //no use
    @Override
    public List<User> readAll() {
        List<User> users = new ArrayList<>();
        try {
            // öffne neue Session
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            //TypedQuery<User> query = em.createQuery("select a from User a", User.class);
            TypedQuery<User> query = em.createQuery("SELECT user FROM User user", User.class);
            users = query.getResultList();

            em.getTransaction().commit();
            em.close();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        return users;
    }

    //needed (load Userdata)
    @Override
    public Optional<User> readById(long id) {
        User user = null;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        user = em.find(User.class , id);

        em.getTransaction().commit();
        em.close();

        return Optional.ofNullable(user);
    }

    //no use
    @Override
    public void update(User user) {

        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            em.close();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }

    //needs user_ID in constructor
    //no use
    @Override
    public void delete(User user) {

       try {
           EntityManager em = emf.createEntityManager();
           em.getTransaction().begin();
           User userInContext = em.merge(user);
           em.remove(userInContext);
           em.getTransaction().commit();
           em.close();
       } catch (IllegalStateException e) {
           e.printStackTrace();
       }
    }

    //read by ID AND password (for login)
    public Optional<User> readByLoginData (int userID, String password) {
        //getResultList to avoid getSingleResult's NoResultException if null
        List<User> users = null;
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            TypedQuery<User> query = em.createQuery("select a from User a where a.userID = ?1 and a.password = ?2", User.class);
            query.setMaxResults(1);
            users = query
                    .setParameter(1, userID)
                    .setParameter(2, password)
                    .getResultList();
            if (users == null || users.isEmpty()) {
                return null;
            }
//          String givenUserID = Integer.toString(userID);
//            user = query
//                    .setParameter(1,userID)
//                    .setParameter(2,password)
//                    .getSingleResult();

            em.getTransaction().commit();
            em.close();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }


        return Optional.ofNullable(users.get(0));
    }

    //with ID only:
    public Optional<User> readByLoginDataOnlyID (int userID) {
        //getResultList to avoid getSingleResult's NoResultException if null
        List<User> users = null;
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            TypedQuery<User> query = em.createQuery("select a from User a where a.userID = ?1", User.class);
            query.setMaxResults(1);
            users = query
                    .setParameter(1, userID)
                    .getResultList();
            if (users == null || users.isEmpty()) {
                return null;
            }
//          String givenUserID = Integer.toString(userID);
//            user = query
//                    .setParameter(1,userID)
//                    .setParameter(2,password)
//                    .getSingleResult();

            em.getTransaction().commit();
            em.close();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }


        return Optional.ofNullable(users.get(0));
    }



}
