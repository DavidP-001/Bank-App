package com.example.bankapp.repository;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public interface CRUDRepositoryJPA<T> extends CRUDRepository<T> {

    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankAppDBjpa");

}
