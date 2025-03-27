package app.persistence;

import app.entities.Cupcake;
import app.entities.Order;
import app.entities.SavedOrders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SavedOrdersMapper {

    public void insertSavedOrder(Order order, ConnectionPool connectionPool) throws SQLException {
        //TODO: Decide with group how the new table for saved_orders should be constructed
        String sql = "INSERT INTO saved_orders VALUES ()";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setInt(1, order.getDate().getDay());
            preparedStatement.setFloat(2, order.getTotal_price());
            preparedStatement.setInt(3, order.getUser_id());
        }

    }

    public void deleteSavedOrder(Cupcake cupcake, ConnectionPool connectionPool) throws SQLException {
        String sql = "DELETE FROM saved_orders WHERE id = ?";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){

            preparedStatement.setInt(1, cupcake.getId());
            int affectedRow = preparedStatement.executeUpdate();

            if(affectedRow > 0) {
                System.out.println("Saved order deleted successfully");
            } else {
                System.out.println("Something went wrong while deleting the order");
            }
        }
    }

    public List<SavedOrders> getAllSavedOrders(ConnectionPool connectionPool) throws SQLException {
        List<SavedOrders> savedOrders = new ArrayList<SavedOrders>();

        String sql = "SELECT * FROM saved_orders";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ){

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                //TODO: Fill this with all the values our soon to be made constructor needs
                SavedOrders savedOrder = new SavedOrders();
                savedOrders.add(savedOrder);
            }
        }
        return savedOrders;
    }
}
