package mate.academy.internetshop.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {

    private static final String DB_NAME = "internetshop.items";
    private static final Logger LOGGER = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item entity) {
        String title = entity.getTitle();
        BigDecimal price = new BigDecimal(entity.getPrice());
        String query = String.format(Locale.ROOT,
                "INSERT INTO %s (title, price) VALUES ('%s', %.2f)",
                DB_NAME, entity.getTitle(), new BigDecimal(entity.getPrice()));
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            LOGGER.warn("Failed to create item", e);
        }
        return entity;
    }

    @Override
    public Optional<Item> get(Long id) {
        String query = String.format("SELECT * FROM %s WHERE item_id=%d)", DB_NAME,
                id);
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Long itemId = rs.getLong("item_id");
                String title = rs.getString("title");
                BigDecimal price = rs.getBigDecimal("price");
                Item item = new Item(title, price.toString());
                item.setId(itemId);
                return Optional.of(item);
            }
        } catch (SQLException e) {
            LOGGER.warn("Failed to get item by id: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Item update(Item entity) {
        Long itemId = entity.getId();
        String title = entity.getTitle();
        BigDecimal price = new BigDecimal(entity.getPrice());
        String query = String.format(Locale.ROOT,
                "UPDATE %s SET title='%s', price=%.2f WHERE item_id=%d",
                DB_NAME, title, price, itemId);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            LOGGER.warn("Failed to update item", e);
        }
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        String query = String.format("DELETE FROM %s WHERE item_id=%d", DB_NAME, id);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            LOGGER.warn("Failed to delete item with id: " + id, e);
        }
        return false;
    }

    @Override
    public boolean delete(Item entity) {
        return deleteById(entity.getId());
    }

    @Override
    public List<Item> getAllEntities() {
        List<Item> items = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", DB_NAME);
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Long itemId = rs.getLong("item_id");
                String title = rs.getString("title");
                BigDecimal price = rs.getBigDecimal("price");
                Item item = new Item(title, price.toString());
                item.setId(itemId);
                items.add(item);
            }
        } catch (SQLException e) {
            LOGGER.warn("Failed to get items from" + DB_NAME + ".items", e);
        }
        return items;
    }
}
