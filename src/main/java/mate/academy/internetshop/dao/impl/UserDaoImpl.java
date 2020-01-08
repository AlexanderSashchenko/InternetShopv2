package mate.academy.internetshop.dao.impl;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.storage.IdGenerator;
import mate.academy.internetshop.storage.Storage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User create(User user) {
        user.setId(IdGenerator.getUserId());
        Storage.users.add(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.ofNullable(Storage.users
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find user with id: "
                        + id)));
    }

    @Override
    public Optional<User> update(User user) {
        return Optional.ofNullable(Storage.users.stream()
                .filter(u -> u.getId().equals(user.getId()))
                .map(u -> user)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find user with id: "
                        + user.getId())));
    }

    @Override
    public boolean delete(Long id) {
        Optional<User> toDelete = get(id);
        return toDelete.map(Storage.users::remove).orElse(false);
    }

    @Override
    public boolean deleteByEntity(User user) {
        return Storage.users.remove(user);
    }

    @Override
    public List<User> getAllEntities() {
        return Storage.users;
    }
}
