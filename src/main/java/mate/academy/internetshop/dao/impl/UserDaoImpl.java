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
        User updatedUser = get(user.getId()).get();
        updatedUser.setId(user.getId());
        updatedUser.setName(user.getName());
        return updatedUser;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<User> toDelete = get(id);
        return Storage.users.remove(toDelete.get());
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
