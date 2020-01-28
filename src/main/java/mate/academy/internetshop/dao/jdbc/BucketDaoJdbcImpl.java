package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket entity) throws DataProcessingException {
        String query = "INSERT INTO buckets (user_id) VALUES (?)";
        try (PreparedStatement statement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, entity.getUserId());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                entity.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to create bucket items" + e);
        }
        insertBucketItems(entity);
        return entity;
    }

    @Override
    public Optional<Bucket> get(Long id) throws DataProcessingException {
        String query = "SELECT * FROM buckets WHERE bucket_id = ?";
        return getBucket(query, id);
    }

    public Optional<Bucket> getBucket(String query, Long id) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            Bucket bucket = new Bucket();
            while (rs.next()) {
                bucket.setUserId(rs.getLong("user_id"));
                bucket.setId(rs.getLong("bucket_id"));
            }
            bucket.setItems(getAllItems(bucket.getId()));
            return Optional.of(bucket);
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get bucket by id: " + id + e);
        }
    }

    @Override
    public Bucket update(Bucket entity) throws DataProcessingException {
        deleteBucketItems(entity);
        insertBucketItems(entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        if (get(id).isPresent()) {
            deleteBucketItems(get(id).get());
        }
        String query = "DELETE FROM buckets WHERE bucket_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to delete bucket with id: " + id + e);
        }
    }

    @Override
    public boolean delete(Bucket entity) throws DataProcessingException {
        return deleteById(entity.getId());
    }

    @Override
    public List<Bucket> getAllEntities() throws DataProcessingException {
        List<Bucket> buckets = new ArrayList<>();
        String query = "SELECT * FROM buckets";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Bucket bucket = new Bucket();
                bucket.setUserId(rs.getLong("user_id"));
                bucket.setId(rs.getLong("bucket_id"));
                bucket.setItems(getAllItems(rs.getLong("bucket_id")));
                buckets.add(bucket);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get buckets list" + e);
        }
        return buckets;
    }

    private void insertBucketItems(Bucket entity) throws DataProcessingException {
        String insertBucketItemsQuery = "INSERT INTO bucket_items (bucket_id, item_id)"
                + " VALUES (?, ?)";
        try (PreparedStatement statement
                     = connection.prepareStatement(insertBucketItemsQuery)) {
            statement.setLong(1, entity.getId());
            for (Item item : entity.getItems()) {
                statement.setLong(2, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to update bucket items" + e);
        }
    }

    private void deleteBucketItems(Bucket entity) throws DataProcessingException {
        String deleteBucketItemsQuery = "DELETE FROM bucket_items WHERE bucket_id=?";
        try (PreparedStatement statement
                     = connection.prepareStatement(deleteBucketItemsQuery)) {
            statement.setLong(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to update bucket items");
        }
    }

    @Override
    public Optional<Bucket> getByUserId(Long userId) throws DataProcessingException {
        String query = "SELECT * FROM buckets WHERE user_id = ?";
        return getBucket(query, userId);
    }

    private List<Item> getAllItems(Long id) throws DataProcessingException {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM bucket_items INNER JOIN items ON bucket_items.item_id "
                + "= items.item_id WHERE bucket_items.bucket_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Item item = new Item(rs.getString("title"), rs.getBigDecimal("price").toString());
                item.setId(rs.getLong("item_id"));
                items.add(item);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot get all items by bucketId " + id + e);
        }
        return items;
    }
}
