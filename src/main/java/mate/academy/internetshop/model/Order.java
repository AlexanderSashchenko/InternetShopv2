package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private Long id;
    private Long userId;
    private List<Item> itemsInOrder;

    public Order() {
        this.itemsInOrder = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItemsInOrder() {
        return itemsInOrder;
    }

    public void setItemsInOrder(List<Item> itemsInOrder) {
        this.itemsInOrder = itemsInOrder;
    }
}
