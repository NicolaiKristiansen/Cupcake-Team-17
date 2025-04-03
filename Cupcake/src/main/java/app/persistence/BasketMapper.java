package app.persistence;

import app.entities.CupcakeBottom;
import app.entities.CupcakeTop;
import app.entities.Order;
import app.entities.Orderline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.javalin.http.Context;

public class BasketMapper {

    CupcakeMapper cupcake_Mapper = new CupcakeMapper();

    // Removed the instance-level orderlinesForBasket list
    // This will be handled by session attributes

    public void createOrderlinesForBasket(Context ctx, ConnectionPool connectionPool) {
        // Initialize prices
        float bottomPrice = 0;
        float topPrice = 0;
        String top_name = "";
        String bottom_name = "";

        // Hent de valgte cupcake IDs og mængde
        String cupcakeTopID = ctx.formParam("cupcaketop");
        String cupcakeBottomID = ctx.formParam("cupcakebottom");
        String quantity = ctx.formParam("quantity");

        // Check for null or empty parameters
        if (cupcakeTopID == null || cupcakeBottomID == null || quantity == null ||
                cupcakeTopID.isEmpty() || cupcakeBottomID.isEmpty() || quantity.isEmpty()) {
            System.out.println("Something is null or empty");
            return;
        }

        int topID = Integer.parseInt(cupcakeTopID);
        int bottomID = Integer.parseInt(cupcakeBottomID);
        int amount = Integer.parseInt(quantity);

        // Get the list of cupcake options
        List<CupcakeBottom> cupcakeBottomList = cupcake_Mapper.getCupcakeBottomOptions(connectionPool);
        List<CupcakeTop> cupcakeTopList = cupcake_Mapper.getCupcakeTopOptions(connectionPool);

        // Get the price of selected cupcake bottom and top
        for (CupcakeBottom cupcakeBottom : cupcakeBottomList) {
            if (cupcakeBottom.getId() == bottomID) {
                bottomPrice = cupcakeBottom.getPrice();
                bottom_name = cupcakeBottom.getName();
            }
        }
        for (CupcakeTop cupcakeTop : cupcakeTopList) {
            if (cupcakeTop.getId() == topID) {
                topPrice = cupcakeTop.getPrice();
                top_name = cupcakeTop.getName();
            }
        }

        // Calculate total price
        float totalprice = (topPrice + bottomPrice) * amount;

        // Create an Orderline object
        Orderline orderline = new Orderline(topID, bottomID, top_name, bottom_name, amount, totalprice);

        // Retrieve the orderlinesForBasket from the session (if any)
        List<Orderline> orderlinesForBasket = ctx.sessionAttribute("orderlinesForBasket");

        // If the list doesn't exist, create a new one
        if (orderlinesForBasket == null) {
            orderlinesForBasket = new ArrayList<>();
        }

        // Add the new orderline to the basket
        orderlinesForBasket.add(orderline);

        // Store the updated list of orderlines back in the session
        ctx.sessionAttribute("orderlinesForBasket", orderlinesForBasket);


    }

    // Retrieve the orderlinesForBasket from the session
    public List<Orderline> getOrderlinesForBasket(Context ctx) {
        return ctx.sessionAttribute("orderlinesForBasket");
    }

    // Calculate and send the total price of cupcakes in the basket
    public float sendTotalPriceOfCupcakes(Context ctx) {
        float basketTotalPrice = 0;

        // Retrieve the list of orderlines from the session
        List<Orderline> orderlinesForBasket = getOrderlinesForBasket(ctx);

        // Calculate the total price
        if (orderlinesForBasket != null) {
            for (Orderline orderline : orderlinesForBasket) {
                basketTotalPrice += orderline.getPrice();
            }
        }

        // Store the total price in the context (to use in the template)
        ctx.attribute("basketTotalPrice", basketTotalPrice);
        return basketTotalPrice;
    }

    public void priceOfAOrder(List<Order> savedorders, Context ctx, ConnectionPool connectionPool) throws SQLException {
        String sql = "SELECT total_price FROM \"order\" JOIN orderline ON \"order\".id = orderline.order_id WHERE saved_order = true and user_id = ? ORDER BY \"order\".id DESC";
        List<Float> prices = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // Loop through all orders and fetch the total price for each one
            for (Order order : savedorders) {
                ps.setInt(1, order.getUser_id());  // Set the user_id for the prepared statement
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    float price = rs.getFloat("total_price");
                    prices.add(price);  // Add each order's total price to the list
                }
            }

            // Set the prices list in the context to be used in the template
            ctx.attribute("orderPrices", prices);
        }
    }

}
