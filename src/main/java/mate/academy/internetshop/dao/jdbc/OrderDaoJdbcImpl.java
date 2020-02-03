package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;

@Dao
public class OrderDaoJdbcImpl extends AbstractDao<Order> implements OrderDao {

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order entity) throws DataProcessingException {
        String query = "INSERT INTO orders (user_id) VALUES (?)";
        try (PreparedStatement statement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, entity.getUserId());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                entity.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to create order items" + e);
        }
        insertOrderItems(entity);
        return entity;
    }

    @Override
    public Optional<Order> get(Long id) throws DataProcessingException {
        String query = "SELECT * FROM orders WHERE order_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong("order_id"));
                order.setUserId(rs.getLong("user_id"));
                order.setItems(getAllItems(id));
                return Optional.of(order);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get order by id: " + id + e);
        }
        return Optional.empty();
    }

    @Override
    public Order update(Order entity) throws DataProcessingException {
        deleteOrderItems(entity);
        insertOrderItems(entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        if (get(id).isPresent()) {
            deleteOrderItems(get(id).get());
        }
        String query = "DELETE FROM orders WHERE order_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to delete order items with id: " + id + e);
        }
    }

    @Override
    public boolean delete(Order entity) throws DataProcessingException {
        return deleteById(entity.getId());
    }

    @Override
    public List<Order> getAllEntities() throws DataProcessingException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong("order_id"));
                order.setUserId(rs.getLong("user_id"));
                order.setItems(getAllItems(rs.getLong("order_id")));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get orders list" + e);
        }
        return orders;
    }

    private void insertOrderItems(Order entity) throws DataProcessingException {
        String insertOrderItemsQuery = "INSERT INTO order_items (order_id, item_id) VALUES (?, ?)";
        try (PreparedStatement statement
                     = connection.prepareStatement(insertOrderItemsQuery)) {
            statement.setLong(1, entity.getId());
            for (Item item : entity.getItems()) {
                statement.setLong(2, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to insert order items" + e);
        }
    }

    private void deleteOrderItems(Order entity) throws DataProcessingException {
        String deleteOrderItemsQuery = "DELETE FROM order_items WHERE order_id=?";
        try (PreparedStatement statement
                     = connection.prepareStatement(deleteOrderItemsQuery)) {
            statement.setLong(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to update order items" + e);
        }
    }

    private List<Item> getAllItems(Long id) throws DataProcessingException {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM order_items INNER JOIN items ON order_items.item_id "
                + "= items.item_id WHERE order_items.order_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Item item = new Item(rs.getString("title"), rs.getBigDecimal("price").toString());
                item.setId(rs.getLong("item_id"));
                items.add(item);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot get all items by orderId " + id + e);
        }
        return items;
    }

    @Override
    public List<Order> getAllUserOrders(Long id) throws DataProcessingException {
        String query = "SELECT * FROM orders WHERE user_id = ?";
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong("order_id"));
                order.setUserId(rs.getLong("user_id"));
                order.setItems(getAllItems(rs.getLong("order_id")));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get all user orders" + e);
        }
        return orders;
    }

    @Override
    public boolean deleteAllUserOrders(Long id) throws DataProcessingException {
        List<Order> orders = getAllUserOrders(id);
        for (Order order : orders) {
            deleteOrderItems(order);
        }
        String query = "DELETE FROM orders WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to delete all user orders" + e);
        }
    }
}
