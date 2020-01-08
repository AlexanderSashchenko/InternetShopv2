package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.storage.IdGenerator;
import mate.academy.internetshop.storage.Storage;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Bucket create(Bucket bucket) {
        bucket.setId(IdGenerator.getBucketId());
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long id) {
        return Optional.ofNullable(Storage.buckets.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find bucket with id: "
                        + id)));
    }

    @Override
    public Bucket update(Bucket bucket) {
        Bucket updatedBucked = get(bucket.getId()).get();
        updatedBucked.setId(bucket.getId());
        updatedBucked.setUserId(bucket.getUserId());
        updatedBucked.setItems(bucket.getItems());
        return updatedBucked;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Bucket> toDelete = get(id);
        return toDelete.map(Storage.buckets::remove).orElse(false);
    }

    @Override
    public boolean delete(Bucket bucket) {
        return Storage.buckets.remove(bucket);
    }

    @Override
    public List<Bucket> getAllEntities() {
        return Storage.buckets;
    }
}
