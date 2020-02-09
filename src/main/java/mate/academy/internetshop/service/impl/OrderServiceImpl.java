package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
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
    public Order create(Order order) throws DataProcessingException {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long id) throws NoSuchElementException, DataProcessingException {
        return orderDao.get(id).orElseThrow(() ->
                new NoSuchElementException("Can't find bucket order id: " + id));
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        return orderDao.deleteById(id);
    }

    @Override
    public boolean delete(Order order) throws DataProcessingException {
        return orderDao.getAllEntities().remove(order);
    }

    @Override
    public List<Order> getAllEntities() throws DataProcessingException {
        return orderDao.getAllEntities();
    }

    @Override
    public List<Order> getUserOrders(User user) throws DataProcessingException {
        return orderDao.getAllUserOrders(user.getId());
    }

    @Override
    public Order completeOrder(List<Item> items, User user) throws DataProcessingException {
        Order newOrder = new Order();
        newOrder.setUserId(user.getId());
        newOrder.setItems(items);
        return orderDao.create(newOrder);
    }

    @Override
    public boolean deleteAllUserOrders(Long id) throws DataProcessingException {
        return orderDao.deleteAllUserOrders(id);
    }
}
