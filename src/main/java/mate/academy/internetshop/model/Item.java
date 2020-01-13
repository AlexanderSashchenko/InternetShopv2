package mate.academy.internetshop.model;

public class Item {

    private Long id;
    private String title;
    private String price;

    public Item(String title, String price) {
        this.title = title;
        this.price = price;
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
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name='" + title + '\'' + ", price=" + price + '}';
    }
}
