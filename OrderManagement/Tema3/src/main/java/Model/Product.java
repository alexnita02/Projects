package Model;

public class Product {


    private int id;
    private String name;
    private int price;
    private int stock;

    public Product() {

    }
    public Product(int productId, String name, int price, int stock) {
        this.id = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int getStock() {
        return stock;
    }


    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}