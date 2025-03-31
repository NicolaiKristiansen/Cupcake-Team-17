package app.persistence;

import app.entities.Order;
import app.entities.Orderline;
import app.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderlineMapper {

    public void insertOrderline(Orderline orderline, ConnectionPool connectionPool) throws SQLException {
        String sql = "INSERT INTO orderline (cupcake_top_id, cupcake_bottom_id, order_id, amount, price) VALUES (?, ?, ?, ?, ?)";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
                ){
            ps.setInt(1, orderline.getCupcake_top_id());
            ps.setInt(2, orderline.getCupcake_bottom_id());
            ps.setInt(3, orderline.getOrder_id());
            ps.setInt(4, orderline.getAmount());
            ps.setFloat(5, orderline.getPrice());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Orderline inserted successfully");
            } else {
                System.out.println("Orderline not inserted successfully");
            }
        }
    }

    public ArrayList<Orderline> getOrderlineByOrderid(User user, ConnectionPool connectionPool) throws SQLException {
        ArrayList<Orderline> orderlines = new ArrayList<>();
        String sql = "SELECT * FROM orderline WHERE order_id = ? ORDER BY order_id ASC";

        List<Order> orders = user.getOrders();
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ){
            for(Order order : orders){
            int orderid = order.getId();

            ps.setInt(1, orderid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int cupcake_top_id = rs.getInt("cupcake_top_id");
                int cupcake_bottom_id = rs.getInt("cupcake_bottom_id");
                int order_id = rs.getInt("order_id");
                int amount = rs.getInt("amount");
                float price = rs.getFloat("price");

                Orderline orderline = new Orderline(id, cupcake_top_id, cupcake_bottom_id, order_id, amount, price);
                orderlines.add(orderline);
            }
        }
        }
        return orderlines;
    }
}
