package app.persistence;




import app.entities.CupcakeBottom;
import app.entities.CupcakeTop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CupcakeMapper {
    //Class made by Nicolai

    public List<CupcakeTop> getCupcakeTopOptions(ConnectionPool connectionPool){

        //Function made by Nicolai
        List<CupcakeTop> cupcakeTops = new ArrayList<CupcakeTop>();

        String sql = "SELECT * FROM cupcake_top";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
                ){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");

                CupcakeTop cupcakeTop = new CupcakeTop(id, name, price);
                cupcakeTops.add(cupcakeTop);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return cupcakeTops;
    }

    public List<CupcakeBottom> getCupcakeBottomOptions(ConnectionPool connectionPool){

        //Function made by Nicolai
        List<CupcakeBottom> cupcakeBottoms = new ArrayList<CupcakeBottom>();

        String sql = "SELECT * FROM cupcake_bottom";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");

                CupcakeBottom cupcakeBottom = new CupcakeBottom(id, name, price);
                cupcakeBottoms.add(cupcakeBottom);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return cupcakeBottoms;
    }

    public void insertCupcakeTop(CupcakeTop cupcakeTop, ConnectionPool connectionPool) throws SQLException {
        //Function made by Nicolai
        String sql = "INSERT INTO cupcake_top (name, price) VALUES (?,?)";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
                ){
            ps.setString(1, cupcakeTop.getName());
            ps.setDouble(2, cupcakeTop.getPrice());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected != 0) {
                System.out.println("Insertion Successful");
            }
            else {
                System.out.println("Insertion Failed");
            }
        }
    }

    public void insertCupcakeBottom(CupcakeBottom cupcakeBottom, ConnectionPool connectionPool) throws SQLException {
        //Function made by Nicolai
        String sql = "INSERT INTO cupcake_bottom (name, price) VALUES (?,?)";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setString(1, cupcakeBottom.getName());
            ps.setDouble(2, cupcakeBottom.getPrice());

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
