package app.controllers;

import app.entities.Order;
import app.persistence.AdminMapper;
import app.persistence.ConnectionPool;
import app.persistence.OrderMapper;

import io.javalin.http.Context;
import java.sql.SQLException;
import java.util.List;

public class AdminController {
    //Class created by Nicolai

    public void giveOrdersToHTML(ConnectionPool connectionPool, Context ctx) throws SQLException {
        //We take our orders from the database and make them usable on html that use the key orders. This ${orders}
        AdminMapper admin_Mapper = new AdminMapper();
        List<Order> orders = admin_Mapper.getOrders(connectionPool);
        ctx.attribute("orders", orders);
    }
}
