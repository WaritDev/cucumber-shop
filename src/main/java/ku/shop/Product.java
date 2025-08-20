package ku.shop;

public class Product {
    private double price;
    private String name;
    private int stock;

    public Product(String name, double price, int stock) {
        if (price < 0) throw new IllegalArgumentException("price must be >= 0");
        if (stock < 0) throw new IllegalArgumentException("stock must be >= 0");
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void cutStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be > 0");
        }
        if (stock < quantity) {
            throw new InsufficientStockException(name, quantity, stock);
        }
        stock -= quantity;
    }

    public boolean hasStock(int quantity) {
        return quantity > 0 && stock >= quantity;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setName(String name) { this.name = name; }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("price must be >= 0");
        this.price = price;
    }

    public void restock(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be > 0");
        this.stock += quantity;
    }
}