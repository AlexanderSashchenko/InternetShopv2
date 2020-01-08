package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long id;
    private Long userId;
    private List<Item> itemsInBucket;

    public Bucket() {
        this.itemsInBucket = new ArrayList<>();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItemsInBucket() {
        return itemsInBucket;
    }

    public void setItemsInBucket(List<Item> itemsInBucket) {
        this.itemsInBucket = itemsInBucket;
    }
}
