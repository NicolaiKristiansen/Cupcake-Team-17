package app.entities;

public class Orderline {
    //Class made by Nicolai

    private int id;
    private int cupcake_top_id;
    private int cupcake_bottom_id;

    private String cupcake_top_name;
    private String cupcake_bottom_name;
    private int order_id;
    private int amount;
    private float price;

    public Orderline(int id, int cupcake_top_id, int cupcake_bottom_id, int order_id, int amount, float price) {
        this.id = id;
        this.cupcake_top_id = cupcake_top_id;
        this.cupcake_bottom_id = cupcake_bottom_id;
        this.order_id = order_id;
        this.amount = amount;
        this.price = price;
    }


    public Orderline(int id, int cupcake_top_id, int cupcake_bottom_id, String cupcake_top_name, String cupcake_bottom_name, int order_id, int amount, float price) {
        this.id = id;
        this.cupcake_top_id = cupcake_top_id;
        this.cupcake_bottom_id = cupcake_bottom_id;
        this.cupcake_top_name = cupcake_top_name;
        this.cupcake_bottom_name = cupcake_bottom_name;
        this.order_id = order_id;
        this.amount = amount;
        this.price = price;
    }

    public Orderline(int cupcake_top_id, int cupcake_bottom_id, String cupcake_top_name, String cupcake_bottom_name, int amount, float total_price) {
        this.cupcake_top_id = cupcake_top_id;
        this.cupcake_bottom_id = cupcake_bottom_id;
        this.cupcake_top_name = cupcake_top_name;
        this.cupcake_bottom_name = cupcake_bottom_name;
        this.order_id = order_id;
        this.amount = amount;
        this.price = total_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCupcake_top_id() {
        return cupcake_top_id;
    }

    public void setCupcake_top_id(int cupcake_top_id) {
        this.cupcake_top_id = cupcake_top_id;
    }



    public int getCupcake_bottom_id() {
        return cupcake_bottom_id;
    }

    public void setCupcake_bottom_id(int cupcake_bottom_id) {
        this.cupcake_bottom_id = cupcake_bottom_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCupcake_top_name() {
        return cupcake_top_name;
    }

    public void setCupcake_top_name(String cupcake_top_name) {
        this.cupcake_top_name = cupcake_top_name;
    }

    public String getCupcake_bottom_name() {
        return cupcake_bottom_name;
    }

    public void setCupcake_bottom_name(String cupcake_bottom_name) {
        this.cupcake_bottom_name = cupcake_bottom_name;
    }

    @Override
    public String toString() {
        return "Orderline{" +
                "id=" + id +
                ", cupcake_top_id=" + cupcake_top_id +
                ", cupcake_bottom_id=" + cupcake_bottom_id +
                ", order_id=" + order_id +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
