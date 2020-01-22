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
        return Storage.users
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public User update(User user) {
        Optional<User> updatedOptionalUser = get(user.getId());
        if (updatedOptionalUser.isPresent()) {
            User updatedUser = updatedOptionalUser.get();
            updatedUser.setId(user.getId());
            updatedUser.setLogin(user.getLogin());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            return updatedUser;
        }
        throw new NoSuchElementException("Can't find bucket with id: " + user.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<User> toDelete = get(id);
        return toDelete.map(Storage.users::remove).orElse(false);
    }

    @Override
    public boolean delete(User user) {
        return Storage.users.remove(user);
    }

    @Override
    public List<User> getAllEntities() {
        return Storage.users;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Storage.users
                .stream()
                .filter(u -> u.getLogin().equalsIgnoreCase(login))
                .findFirst();
    }
}
