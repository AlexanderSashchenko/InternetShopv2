package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User get(Long id) throws NoSuchElementException {
        if (userDao.get(id).isPresent()) {
            return userDao.get(id).get();
        } else {
            throw new NoSuchElementException("Can't find user with id: " + id);
        }
    }

    @Override
    public User update(User user) throws NoSuchElementException {
        userDao.update(user);
        if (userDao.get(user.getId()).isPresent()) {
            return userDao.get(user.getId()).get();
        } else {
            throw new NoSuchElementException("Can't find user with id: " + user.getId());
        }
    }

    @Override
    public boolean deleteById(Long id) {
        return userDao.deleteById(id);
    }

    @Override
    public boolean delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public List<User> getAllEntities() {
        return userDao.getAllEntities();
    }
}
