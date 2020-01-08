package mate.academy.internetshop;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class Controller {

    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserService userService;
    @Inject
    private static ItemService itemService;
    @Inject
    private static OrderService orderService;

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Bucket newBucket = new Bucket();
        Item laptop = new Item("Apple", 1250.50);

        User newUser = new User("Alex");

        bucketService.create(newBucket);
        userService.create(newUser);

        itemService.create(laptop);
        bucketService.addItem(newBucket, laptop);
        System.out.println(newBucket.toString());

        Item smartphone = new Item("Samsung", 400D);

        itemService.create(smartphone);
        bucketService.addItem(newBucket, smartphone);
        System.out.println(newBucket.toString());

        orderService.completeOrder(newBucket.getItems(), newUser);

        bucketService.deleteItem(newBucket, smartphone);
        System.out.println(newBucket.toString());

        orderService.completeOrder(newBucket.getItems(), newUser);

        orderService.getUserOrders(newUser);
    }
}
