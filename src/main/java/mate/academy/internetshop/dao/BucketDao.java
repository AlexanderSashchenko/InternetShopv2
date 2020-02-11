package mate.academy.internetshop.dao;

import java.util.Optional;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.model.Bucket;

public interface BucketDao extends DaoGeneric<Bucket, Long> {
    Optional<Bucket> getByUserId(Long id) throws DataProcessingException;
}
