package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private static OrderDao orderDao;

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public Optional<Order> update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }

    @Override
    public boolean deleteByEntity(Order order) {
        return orderDao.getAllEntities().remove(order);
    }

    @Override
    public List<Order> getAllEntities() {
        return orderDao.getAllEntities();
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getAllEntities()
                .stream()
                .filter(o -> o.getUserId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Order completeOrder(List<Item> items, User user) {
        Order newOrder = new Order();
        newOrder.setUserId(user.getId());
        newOrder.setItemsInOrder(items);
        orderDao.create(newOrder);
        return newOrder;
    }
}
