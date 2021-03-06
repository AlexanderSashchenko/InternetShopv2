package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.exceptions.DataProcessingException;

public interface DaoGeneric<T, N> {
    T create(T entity) throws DataProcessingException;

    Optional<T> get(N id) throws DataProcessingException;

    T update(T entity) throws DataProcessingException;

    boolean deleteById(N id) throws DataProcessingException;

    boolean delete(T entity) throws DataProcessingException;

    List<T> getAllEntities() throws DataProcessingException;
}
