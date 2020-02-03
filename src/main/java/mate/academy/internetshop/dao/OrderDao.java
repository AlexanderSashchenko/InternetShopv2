package mate.academy.internetshop.dao;

import java.util.List;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.model.Order;

public interface OrderDao extends DaoGeneric<Order, Long> {

    List<Order> getAllUserOrders(Long id) throws DataProcessingException;

    boolean deleteAllUserOrders(Long id) throws DataProcessingException;
}
