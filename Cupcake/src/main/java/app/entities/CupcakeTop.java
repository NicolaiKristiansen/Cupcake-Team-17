package app.entities;

public class CupcakeTop {

    private int id;
    private String name;
    private float price;

    public CupcakeTop(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public CupcakeTop(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CupcakeTop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
