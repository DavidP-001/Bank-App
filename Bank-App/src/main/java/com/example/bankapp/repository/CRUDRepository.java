package com.example.bankapp.repository;
import java.util.List;
import java.util.Optional;
public interface CRUDRepository<T> {

    void insert(T entity);

    List<T> readAll();
    Optional<T> readById(long id);

    void update(T entity);

    void delete(T entity);

}
