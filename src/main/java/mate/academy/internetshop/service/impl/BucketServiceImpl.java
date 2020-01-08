package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

import java.util.List;
import java.util.Optional;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private static BucketDao bucketDao;

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Optional<Bucket> get(Long id) {
        return bucketDao.get(id);
    }

    @Override
    public Optional<Bucket> update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean delete(Long id) {
        return bucketDao.delete(id);
    }

    @Override
    public boolean deleteByEntity(Bucket bucket) {
        return bucketDao.deleteByEntity(bucket);
    }

    @Override
    public List<Bucket> getAllEntities() {
        return bucketDao.getAllEntities();
    }

    @Override
    public Bucket addItem(Bucket bucket, Item item) {
        List<Item> items = bucketDao.get(bucket.getId()).map(Bucket::getItemsInBucket).get();
        items.add(item);
        bucket.setItemsInBucket(items);
        return bucket;
    }

    @Override
    public Bucket deleteItem(Bucket bucket, Item item) {
        List<Item> items = bucketDao.get(bucket.getId()).map(Bucket::getItemsInBucket).get();
        items.remove(item);
        bucket.setItemsInBucket(items);
        return bucket;
    }

    @Override
    public Bucket clear(Bucket bucket) {
        List<Item> items = bucketDao.get(bucket.getId()).map(Bucket::getItemsInBucket).get();
        items.clear();
        bucket.setItemsInBucket(items);
        return bucket;
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) {
        return bucketDao.get(bucket.getId()).map(Bucket::getItemsInBucket).get();
    }
}
