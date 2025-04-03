package app.persistence;

import app.entities.Order;
import app.entities.User;
import io.javalin.http.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class OrderMapper {
    Order order;

    public void insertOrder(Order order, ConnectionPool connectionPool) throws SQLException {
        //Function made by Nicolai
        //This function will be used to send an order and then insert it into our database
        String sql = "INSERT INTO public.order (date, total_price, user_id, saved_order) VALUES (?,?,?, ?)";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
                ){

                java.sql.Date date = order.getDate();

                //Since id gets generated automatically we don't need to add them
                ps.setDate(1, date);
                ps.setFloat(2, order.getTotal_price());
                ps.setInt(3, order.getUser_id());
                ps.setBoolean(4, order.isSaved_order());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order Inserted Successfully");
            }
            else {
                System.out.println("Order Insertion Failed");
            }
        }
    }

    public void setSavedOrdersForUser(User user, ConnectionPool connectionPool) throws SQLException {
        //Used to give a list of orders to a user
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM \"public\".\"order\" WHERE user_id = ? ORDER BY id DESC";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
                ){
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Date date = rs.getDate("date");
                float total_price = rs.getFloat("total_price");
                int user_id = rs.getInt("user_id");
                boolean saved_order = rs.getBoolean("saved_order");
                if(saved_order) {
                    Order order = new Order(id, date, total_price, user_id, saved_order);
                    orders.add(order);
                }

            }
            user.setOrders(orders);
        }
    }

    public Order makeOrder(User user, float total_price, ConnectionPool connectionPool) throws SQLException {
        Date date = new Date(System.currentTimeMillis()); //We need the current date
        int user_id = user.getId(); //When the user logs in it makes a user object, that is the object we need
        boolean saved_order = false; //This will be based on if in basket the checkbox is saying yes or no



        Order order = new Order(date, total_price, user_id, saved_order);
        return order;
    }

    public Order getNewestOrder(ConnectionPool connectionPool) throws SQLException {
        String sql = "SELECT * FROM \"public\".\"order\" ORDER BY id DESC LIMIT 1";
        Order order = null;

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date date = rs.getDate("date");
                float total_price = rs.getFloat("total_price");
                int user_id = rs.getInt("user_id");
                boolean saved_order = rs.getBoolean("saved_order");

                 order = new Order(id, date, total_price, user_id, saved_order);

            }

        }
        return order;
    }

    public void checkIfOrderShouldBeSavedForUser(Order order, Context ctx, ConnectionPool connectionPool) throws SQLException {
        String savedOrNot = ctx.formParam("savedOrder");
        boolean shouldItBeSaved = false;

        if (savedOrNot != null && savedOrNot.equals("on")) {
            shouldItBeSaved = true;
        }

        // Set the order's saved status
        order.setSaved_order(shouldItBeSaved);
    }

}
