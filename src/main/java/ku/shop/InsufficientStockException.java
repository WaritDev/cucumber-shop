package ku.shop;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String productName, int requested, int available) {
        super("Stock not enough for product '" + productName + "'. requested=" + requested + ", available=" + available);
    }
}