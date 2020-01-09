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
        return Optional.of(Storage.items
                .stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find item with id: "
                        + id)));
    }

    @Override
    public Item update(Item item) {
        Item updatedItem = get(item.getId()).get();
        updatedItem.setId(item.getId());
        updatedItem.setName(item.getName());
        updatedItem.setPrice(item.getPrice());
        return updatedItem;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Item> toDelete = get(id);
        return Storage.items.remove(toDelete.get());
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
