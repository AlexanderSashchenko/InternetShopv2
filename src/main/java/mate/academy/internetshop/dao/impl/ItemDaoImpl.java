package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.storage.IdGenerator;
import mate.academy.internetshop.storage.Storage;

@Dao
public class ItemDaoImpl implements ItemDao {

    @Override
    public Item create(Item item) {
        item.setId(IdGenerator.getItemId());
        Storage.items.add(item);
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        return Storage.items
                .stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    @Override
    public Item update(Item item) {
        Optional<Item> updatedOptionalItem = get(item.getId());
        if (updatedOptionalItem.isPresent()) {
            Item updatedItem = updatedOptionalItem.get();
            updatedItem.setId(item.getId());
            updatedItem.setTitle(item.getTitle());
            updatedItem.setPrice(item.getPrice());
            return updatedItem;
        }
        throw new NoSuchElementException("Can't find bucket with id: " + item.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Item> toDelete = get(id);
        return toDelete.map(Storage.items::remove).orElse(false);
    }

    @Override
    public boolean delete(Item item) {
        return Storage.items.remove(item);
    }

    @Override
    public List<Item> getAllEntities() {
        return Storage.items;
    }
}
