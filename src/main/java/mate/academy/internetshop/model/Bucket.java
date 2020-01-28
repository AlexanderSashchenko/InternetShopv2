package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bucket {

    private Long id;
    private Long userId;
    private List<Item> items;

    public Bucket(Long userId) {
        this.userId = userId;
        this.items = new ArrayList<>();
    }

    public Bucket() {
        this.items = new ArrayList<>();
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Bucket{" + "id=" + id + ", userId=" + userId + ", items=" + items + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bucket bucket = (Bucket) o;
        return Objects.equals(id, bucket.id)
                && Objects.equals(userId, bucket.userId)
                && Objects.equals(items, bucket.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, items);
    }
}
