package app.persistence;

import app.entities.Order;
import app.entities.OrdersAndUsers;
import app.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminMapper {
    //Class made by Nicolai

    public static void deleteOrder(int order, ConnectionPool connectionPool) throws SQLException {
        //Function made by Nicolai
        //In the html we need to make it so when you select an order and press delete it calls this with the order as the parameter

        String sql = "DELETE FROM \"public\".\"order\" WHERE id = ?";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setInt(1, order);

            int altereddatabase = preparedStatement.executeUpdate();
            if(altereddatabase > 0){
                System.out.println("Order deleted successfully");
            }
            else {
                System.out.println("Order deletion failed");
            }
        }
    }

    public void updateUsersAllowance(User user, float amount, ConnectionPool connectionPool) throws SQLException {
        //Function made by Nicolai
        //The parameters are the user we select and the amount we want to insert into the user
        String sql = "UPDATE \"user\" SET allowance = ? WHERE id = ?";

        try (Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setFloat(1, amount);
            preparedStatement.setInt(2, user.getId());

            int altereddatabase = preparedStatement.executeUpdate();
            if (altereddatabase > 0){
                System.out.println("User allowance updated successfully");
            }else {
                System.out.println("User allowance update failed");
            }

        }
    }

    public List<User> getAllUsers(ConnectionPool connectionPool) throws SQLException {
        //Function made by Nicolai
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM \"user\"";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                float money = resultSet.getFloat("money");
                String role = resultSet.getString("role");

                User user = new User(id, email, password, money, role);
                users.add(user);
            }
        }
        return users;
    }


    // to be made
    public List<User> getAllUsersWithoutAdmin(ConnectionPool connectionPool) throws SQLException {
        //Function made by Nicolai
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM \"user\"";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                float money = resultSet.getFloat("money");
                String role = resultSet.getString("role");

                User user = new User(id, email, password, money, role);
                users.add(user);
            }
        }
        return users;
    }

    public List<OrdersAndUsers> getOrdersAndUsers(ConnectionPool connectionPool) throws SQLException {
        //Function made by Nicolai
        ArrayList<OrdersAndUsers> orders = new ArrayList<OrdersAndUsers>();
        String sql = "SELECT public.order.id, public.user.email FROM public.order JOIN public.user ON user_id = public.user.id";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ){

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("email");


                OrdersAndUsers ordersAndUsers = new OrdersAndUsers(id, name);
                orders.add(ordersAndUsers);
            }
        }
        return orders;
    }


    public static List<Order> getOrders(ConnectionPool connectionPool) throws SQLException {
        //Function made by Nicolai
        ArrayList<Order> orders = new ArrayList<Order>();
        String sql = "SELECT * FROM \"order\"";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ){

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("id");
                Date date = rs.getDate("date");
                float total_price = rs.getFloat("total_price");
                int user_id = rs.getInt("user_id");
                boolean saved_order = rs.getBoolean("saved_order");

                Order order = new Order(id, date, total_price, user_id, saved_order);
                orders.add(order);
            }
        }
        return orders;
    }
}
