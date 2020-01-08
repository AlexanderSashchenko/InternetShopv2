package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;

public interface DaoGeneric<T, N> {
    T create(T entity);

    Optional<T> get(N id);

    Optional<T> update(T entity);

    boolean delete(N id);

    boolean deleteByEntity(T entity);

    List<T> getAllEntities();
}
