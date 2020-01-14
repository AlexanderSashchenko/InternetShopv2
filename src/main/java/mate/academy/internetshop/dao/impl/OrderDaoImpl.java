package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.storage.IdGenerator;
import mate.academy.internetshop.storage.Storage;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        order.setId(IdGenerator.getOrderId());
        Storage.orders.add(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders
                .stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }

    @Override
    public Order update(Order order) {
        Optional<Order> updatedOptionalOrder = get(order.getId());
        if (updatedOptionalOrder.isPresent()) {
            Order updatedOrder = updatedOptionalOrder.get();
            updatedOrder.setId(order.getId());
            updatedOrder.setUserId(order.getUserId());
            updatedOrder.setItems(order.getItems());
            return updatedOrder;
        } else {
            throw new NoSuchElementException("Can't find bucket with id: " + order.getId());
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Order> toDelete = get(id);
        return toDelete.map(Storage.orders::remove).orElse(false);
    }

    @Override
    public boolean delete(Order order) {
        return Storage.orders.remove(order);
    }

    @Override
    public List<Order> getAllEntities() {
        return Storage.orders;
    }
}
