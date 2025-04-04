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

    public ArrayList<Orderline> getOrderlineByOrderid(User user, ConnectionPool connectionPool, int orderId) throws SQLException {
        ArrayList<Orderline> orderlines = new ArrayList<>();
        String sql = "SELECT orderline.id, orderline.cupcake_top_id, " +
                "orderline.cupcake_bottom_id, cupcake_top.top_name, " +
                "cupcake_bottom.bottom_name, orderline.order_id, orderline.amount, orderline.price " +
                "FROM public.orderline " +
                "JOIN cupcake_top ON orderline.cupcake_top_id = cupcake_top.id " +
                "JOIN cupcake_bottom ON orderline.cupcake_bottom_id = cupcake_bottom.id " +
                "WHERE order_id = ? " +
                "ORDER BY id ASC";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, orderId);  // Only fetch orderlines for the specific order
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int cupcake_top_id = rs.getInt("cupcake_top_id");
                int cupcake_bottom_id = rs.getInt("cupcake_bottom_id");
                String cupcake_top_name = rs.getString("top_name");
                String cupcake_bottom_name = rs.getString("bottom_name");
                int order_id = rs.getInt("order_id");
                int amount = rs.getInt("amount");
                float price = rs.getFloat("price");

                Orderline orderline = new Orderline(id, cupcake_top_id, cupcake_bottom_id, cupcake_top_name, cupcake_bottom_name, order_id, amount, price);
                orderlines.add(orderline);
            }
        }
        return orderlines;
    }

}
