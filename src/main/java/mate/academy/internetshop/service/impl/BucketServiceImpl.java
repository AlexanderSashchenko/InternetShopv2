package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.internetshop.dao.BucketDao;
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
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long id) throws NoSuchElementException {
        if (bucketDao.get(id).isPresent()) {
            return bucketDao.get(id).get();
        } else {
            throw new NoSuchElementException("Can't find bucket with id: " + id);
        }
    }

    @Override
    public Bucket update(Bucket bucket) throws NoSuchElementException {
        if (bucketDao.get(bucket.getId()).isPresent()) {
            bucketDao.update(bucket);
            return bucketDao.get(bucket.getId()).get();
        } else {
            throw new NoSuchElementException("Can't find bucket: " + bucket.toString());
        }
    }

    @Override
    public boolean deleteById(Long id) {
        return bucketDao.deleteById(id);
    }

    @Override
    public boolean delete(Bucket bucket) {
        return bucketDao.delete(bucket);
    }

    @Override
    public List<Bucket> getAllEntities() {
        return bucketDao.getAllEntities();
    }

    @Override
    public void addItem(Bucket bucket, Item item) {
        bucket.getItems().add(item);
        bucketDao.update(bucket);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) {
        bucket.getItems().remove(item);
        bucketDao.update(bucket);
    }

    @Override
    public void clear(Bucket bucket) {
        bucket.getItems().clear();
        bucketDao.update(bucket);
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) {
        return bucket.getItems();
    }
}
