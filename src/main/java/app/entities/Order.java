package app.entities;

import java.sql.Date;

public class Order {
    //Class made by Nicolai

    private int id;
    private Date date;
    private float total_price;
    private int user_id;

    public Order(int id, Date date, float total_price, int user_id) {
        this.id = id;
        this.date = date;
        this.total_price = total_price;
        this.user_id = user_id;
    }

    public Order(Date date, float total_price, int user_id) {
        this.date = date;
        this.total_price = total_price;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", total_price=" + total_price +
                ", user_id=" + user_id +
                '}';
    }
}