package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.storage.IdGenerator;
import mate.academy.internetshop.storage.Storage;

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
        return Optional.of(Storage.users
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find user with id: "
                        + id)));
    }

    @Override
    public User update(User user) {
        Optional<User> updatedUser = get(user.getId());
        if (updatedUser.isPresent()) {
            updatedUser.get().setId(user.getId());
            updatedUser.get().setLogin(user.getLogin());
            updatedUser.get().setPassword(user.getPassword());
            updatedUser.get().setEmail(user.getEmail());
            updatedUser.get().setFirstName(user.getFirstName());
            updatedUser.get().setLastName(user.getLastName());
            return updatedUser.get();
        } else {
            throw new NoSuchElementException("Can't find bucket with id: " + user.getId());
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<User> toDelete = get(id);
        if (toDelete.isPresent()) {
            return Storage.users.remove(toDelete.get());
        } else {
            throw new NoSuchElementException("Can't find user with id: " + id);
        }
    }

    @Override
    public boolean delete(User user) {
        return Storage.users.remove(user);
    }

    @Override
    public List<User> getAllEntities() {
        return Storage.users;
    }
}
