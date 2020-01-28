package mate.academy.internetshop.service;

import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.model.User;

public interface UserService extends ServiceGeneric<User, Long> {

    User login(String login, String password)
            throws AuthenticationException, DataProcessingException;
}
