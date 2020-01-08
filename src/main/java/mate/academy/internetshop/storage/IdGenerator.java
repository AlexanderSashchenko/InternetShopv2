package mate.academy.internetshop.storage;

public class IdGenerator {
    private static long itemId = 0L;
    private static long userId = 0L;
    private static long bucketId = 0L;
    private static long orderId = 0L;

    public static Long getItemId() {
        return itemId++;
    }

    public static Long getUserId() {
        return userId++;
    }

    public static Long getBucketId() {
        return bucketId++;
    }

    public static Long getOrderId() {
        return orderId++;
    }
}
