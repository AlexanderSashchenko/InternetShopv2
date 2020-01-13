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
        return Optional.of(Storage.buckets.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find bucket with id: "
                        + id)));
    }

    @Override
    public Bucket update(Bucket bucket) {
        Optional<Bucket> updatedBucked = get(bucket.getId());
        if (updatedBucked.isPresent()) {
            updatedBucked.get().setId(bucket.getId());
            updatedBucked.get().setUserId(bucket.getUserId());
            updatedBucked.get().setItems(bucket.getItems());
            return updatedBucked.get();
        } else {
            throw new NoSuchElementException("Can't find bucket with id: " + bucket.getId());
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Bucket> toDelete = get(id);
        if (toDelete.isPresent()) {
            return Storage.buckets.remove(toDelete.get());
        } else {
            throw new NoSuchElementException("Can't find bucket with id: " + id);
        }
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
