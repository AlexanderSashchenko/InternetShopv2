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
        return Optional.of(Storage.orders
                .stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find order with id: "
                        + id)));
    }

    @Override
    public Order update(Order order) {
        Order updatedOrder = get(order.getId()).get();
        updatedOrder.setId(order.getId());
        updatedOrder.setUserId(order.getUserId());
        updatedOrder.setItems(order.getItems());
        return updatedOrder;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Order> toDelete = get(id);
        return Storage.orders.remove(toDelete.get());
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
