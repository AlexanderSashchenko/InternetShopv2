package mate.academy.internetshop.dao.impl;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.storage.IdGenerator;
import mate.academy.internetshop.storage.Storage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Bucket create(Bucket bucket) {
        bucket.setId(IdGenerator.getBucketId());
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
    public Optional<Bucket> update(Bucket bucket) {
        return Optional.ofNullable(Storage.buckets.stream()
                .filter(b -> b.getId().equals(bucket.getId()))
                .map(b -> bucket)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find bucket with id: "
                        + bucket.getId())));//not working
    }

    @Override
    public boolean delete(Long id) {
        Optional<Bucket> toDelete = get(id);
        return toDelete.map(Storage.buckets::remove).orElse(false);
    }

    @Override
    public boolean deleteByEntity(Bucket bucket) {
        return Storage.buckets.remove(bucket);
    }

    @Override
    public List<Bucket> getAllEntities() {
        return Storage.buckets;
    }
}
