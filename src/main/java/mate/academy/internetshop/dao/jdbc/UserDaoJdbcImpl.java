package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> findByLogin(String login) throws DataProcessingException {
        String query = "SELECT * FROM users WHERE login = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = extractUser(rs);
                user.setRoles(getAllRoles(rs.getLong("user_id")));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to find user by login" + e);
        }
        return Optional.empty();
    }

    @Override
    public User create(User entity) throws DataProcessingException {
        String query = "INSERT INTO users (login, password, salt, email, first_name, last_name) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setBytes(3, entity.getSalt());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getFirstName());
            statement.setString(6, entity.getLastName());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                entity.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to create user" + e);
        }
        insertUserRoles(entity);
        return entity;
    }

    @Override
    public Optional<User> get(Long id) throws DataProcessingException {
        String query = "SELECT * FROM users WHERE user_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = extractUser(rs);
                user.setRoles(getAllRoles(id));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get user by id" + e);
        }
        return Optional.empty();
    }

    @Override
    public User update(User entity) throws DataProcessingException {
        String query = "UPDATE users SET login=?, password=?, salt=?, "
                + "email=?, first_name=?, last_name=? WHERE user_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setBytes(3, entity.getSalt());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getFirstName());
            statement.setString(6, entity.getLastName());
            statement.setLong(7, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to update user" + e);
        }
        deleteUserRoles(entity);
        insertUserRoles(entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        if (get(id).isPresent()) {
            deleteUserRoles(get(id).get());
        } else {
            throw new DataProcessingException("Failed to delete user");
        }
        String query = "DELETE FROM users WHERE user_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to delete user" + e);
        }
    }

    @Override
    public boolean delete(User entity) throws DataProcessingException {
        return deleteById(entity.getId());
    }

    @Override
    public List<User> getAllEntities() throws DataProcessingException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = extractUser(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get users list" + e);
        }
        return users;
    }

    private void deleteUserRoles(User entity) throws DataProcessingException {
        String deleteUserRolesQuery = "DELETE FROM user_roles WHERE user_id=?";
        try (PreparedStatement statement = connection.prepareStatement(deleteUserRolesQuery)) {
            statement.setLong(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to delete user" + e);
        }
    }

    private void insertUserRoles(User entity) throws DataProcessingException {
        String insertUserRolesQuery = "INSERT INTO user_roles (user_id, role_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertUserRolesQuery)) {
            statement.setLong(1, entity.getId());
            statement.setLong(2, 1);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to create user" + e);
        }
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User(rs.getString("login"));
        user.setId(rs.getLong("user_id"));
        user.setPassword(rs.getString("password"));
        user.setSalt(rs.getBytes("salt"));
        user.setEmail(rs.getString("email"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        return user;
    }

    private Set<Role> getAllRoles(Long id) throws DataProcessingException {
        String query = "SELECT * FROM user_roles INNER JOIN roles "
                + "ON user_roles.role_id = roles.role_id WHERE user_roles.user_id = ?";
        Set<Role> roles = new LinkedHashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Role role = Role.of(rs.getString("role_name"));
                roles.add(role);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to set user roles" + e);
        }
        return roles;
    }
}
