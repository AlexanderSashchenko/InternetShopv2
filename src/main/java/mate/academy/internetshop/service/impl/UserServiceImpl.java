package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) throws DataProcessingException {
        return userDao.create(user);
    }

    @Override
    public User get(Long id) throws NoSuchElementException, DataProcessingException {
        return userDao.get(id).orElseThrow(() ->
                new NoSuchElementException("Can't find user with id: " + id));
    }

    @Override
    public User update(User user) throws DataProcessingException {
        return userDao.update(user);
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        return userDao.deleteById(id);
    }

    @Override
    public boolean delete(User user) throws DataProcessingException {
        return userDao.delete(user);
    }

    @Override
    public List<User> getAllEntities() throws DataProcessingException {
        return userDao.getAllEntities();
    }

    @Override
    public User login(String login, String password) throws AuthenticationException,
            DataProcessingException {
        Optional<User> user = userDao.findByLogin(login);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }
        throw new AuthenticationException("Incorrect login or password!");
    }
}
