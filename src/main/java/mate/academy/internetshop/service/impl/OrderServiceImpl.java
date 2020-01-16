package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private static OrderDao orderDao;

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long id) throws NoSuchElementException {
        Optional<Order> order = orderDao.get(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new NoSuchElementException("Can't find order with id: " + id);
        }
    }

    @Override
    public Order update(Order order) throws NoSuchElementException {
        return orderDao.update(order);
    }

    @Override
    public boolean deleteById(Long id) {
        return orderDao.deleteById(id);
    }

    @Override
    public boolean delete(Order order) {
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
        newOrder.setItems(items);
        return orderDao.create(newOrder);
    }
}
