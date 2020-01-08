package mate.academy.internetshop.dao.impl;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.storage.IdGenerator;
import mate.academy.internetshop.storage.Storage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        return Optional.ofNullable(Storage.items
                .stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find item with id: "
                        + id)));
    }

    @Override
    public Optional<Item> update(Item item) {
        return Optional.ofNullable(Storage.items.stream()
                .filter(i -> i.getId().equals(item.getId()))
                .map(i -> item)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find item with id: "
                        + item.getId())));
    }

    @Override
    public boolean delete(Long id) {
        Optional<Item> toDelete = get(id);
        return toDelete.map(Storage.items::remove).orElse(false);
    }

    @Override
    public boolean deleteByEntity(Item item) {
        return Storage.items.remove(item);
    }

    @Override
    public List<Item> getAllEntities() {
        return Storage.items;
    }
}
