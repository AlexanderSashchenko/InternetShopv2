package mate.academy.internetshop.model;


import java.math.BigDecimal;

public class Item {

    private Long id;
    private String title;
    private BigDecimal price;

    public Item(String title, String price) {
        this.title = title;
        this.price = new BigDecimal(price);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return String.valueOf(price);
    }

    public void setPrice(String price) {
        this.price = new BigDecimal(price);
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name='" + title + '\'' + ", price=" + price + '}';
    }
}
