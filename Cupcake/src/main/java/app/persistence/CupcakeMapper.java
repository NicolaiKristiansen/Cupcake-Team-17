package app.persistence;




import app.entities.Cupcake;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CupcakeMapper {
    //Class made by Nicolai

    public List<Cupcake> getCupcakeOptions(ConnectionPool connectionPool){

        //Function made by Nicolai
        List<Cupcake> cupcakes = new ArrayList<Cupcake>();

        String sql = "SELECT * FROM cupcake";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
                ){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");

                Cupcake cupcake = new Cupcake(id, name, price);
                cupcakes.add(cupcake);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return cupcakes;
    }

    public void insertCupcake(Cupcake cupcake, ConnectionPool connectionPool) throws SQLException {
        //Function made by Nicolai
        String sql = "INSERT INTO cupcake (name, price) VALUES (?,?)";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
                ){
            ps.setString(1, cupcake.getName());
            ps.setDouble(2, cupcake.getPrice());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected != 0) {
                System.out.println("Insertion Successful");
            }
            else {
                System.out.println("Insertion Failed");
            }
        }
    }
}
