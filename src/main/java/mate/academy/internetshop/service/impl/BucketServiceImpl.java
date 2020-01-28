package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private static BucketDao bucketDao;

    @Override
    public Bucket create(Bucket bucket) throws DataProcessingException {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long id) throws NoSuchElementException, DataProcessingException {
        return bucketDao.get(id).orElseThrow(() ->
                new NoSuchElementException("Can't find item with id: " + id));
    }

    @Override
    public Bucket update(Bucket bucket) throws DataProcessingException {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        return bucketDao.deleteById(id);
    }

    @Override
    public boolean delete(Bucket bucket) throws DataProcessingException {
        return bucketDao.delete(bucket);
    }

    @Override
    public List<Bucket> getAllEntities() throws DataProcessingException {
        return bucketDao.getAllEntities();
    }

    @Override
    public void addItem(Bucket bucket, Item item) throws DataProcessingException {
        bucket.getItems().add(item);
        bucketDao.update(bucket);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) throws DataProcessingException {
        bucket.getItems().remove(item);
        bucketDao.update(bucket);
    }

    @Override
    public void clear(Bucket bucket) throws DataProcessingException {
        bucket.getItems().clear();
        bucketDao.update(bucket);
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) {
        return bucket.getItems();
    }

    @Override
    public Bucket getByUserId(Long userId) throws DataProcessingException {
        Optional<Bucket> bucket = bucketDao.getByUserId(userId);
        if (bucket.isPresent()) {
            return bucket.get();
        }
        return bucketDao.create(new Bucket(userId));
    }
}
