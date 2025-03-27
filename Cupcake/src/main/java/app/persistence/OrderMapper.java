package app.persistence;

import app.entities.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class OrderMapper {
    //Class made by Nicolai


    public void insertOrder(Order order, ConnectionPool connectionPool) throws SQLException {
        //Function made by Nicolai
        //This function will be used to send an order and then insert it into our database
        String sql = "INSERT INTO order VALUES (?,?,?)";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
                ){

                //Since id gets generated automatically we don't need to add them
                ps.setDate(1, order.getDate());
                ps.setFloat(2, order.getTotal_price());
                ps.setInt(3, order.getUser_id());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order Inserted Successfully");
            }
            else {
                System.out.println("Order Insertion Failed");
            }
        }
    }
}
