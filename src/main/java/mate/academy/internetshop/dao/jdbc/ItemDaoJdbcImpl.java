package mate.academy.internetshop.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item entity) throws DataProcessingException {
        String query = "INSERT INTO items (title, price) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getTitle());
            statement.setBigDecimal(2, new BigDecimal(entity.getPrice()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                entity.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to create item" + e);
        }
        return entity;
    }

    @Override
    public Optional<Item> get(Long id) throws DataProcessingException {
        String query = "SELECT * FROM items WHERE item_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Item item = new Item(rs.getString("title"), rs.getBigDecimal("price").toString());
                item.setId(rs.getLong("item_id"));
                return Optional.of(item);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get item by id: " + id + e);
        }
        return Optional.empty();
    }

    @Override
    public Item update(Item entity) throws DataProcessingException {
        String query = "UPDATE items SET title=?, price=? WHERE item_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getTitle());
            statement.setBigDecimal(2, new BigDecimal(entity.getPrice()));
            statement.setLong(3, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to update item" + e);
        }
        return entity;
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        String query = "DELETE FROM items WHERE item_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to delete item with id: " + id + e);
        }
    }

    @Override
    public boolean delete(Item entity) throws DataProcessingException {
        return deleteById(entity.getId());
    }

    @Override
    public List<Item> getAllEntities() throws DataProcessingException {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM items";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Item item = new Item(rs.getString("title"), rs.getBigDecimal("price").toString());
                item.setId(rs.getLong("item_id"));
                items.add(item);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get items list" + e);
        }
        return items;
    }
}
