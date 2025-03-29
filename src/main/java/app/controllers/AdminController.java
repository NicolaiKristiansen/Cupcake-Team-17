package app.controllers;

import app.entities.Order;
import app.entities.User;
import app.persistence.AdminMapper;
import app.persistence.ConnectionPool;


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

    public void giveUserToHTML(ConnectionPool connectionPool, Context ctx) throws SQLException {
        //We take our users from the database and make them usable on html that use the key users. This $(users)
        AdminMapper admin_Mapper = new AdminMapper();
        List<User> users = admin_Mapper.getAllUsers(connectionPool);
        ctx.attribute("users", users);
    }
}
