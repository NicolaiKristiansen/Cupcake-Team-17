package app.controllers;

import app.entities.Order;
import app.entities.OrdersAndUsers;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.AdminMapper;
import app.persistence.ConnectionPool;


import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.sql.SQLException;
import java.util.List;

public class AdminController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool){

        app.get("order", ctx -> orders(ctx, connectionPool));
        app.get("user", ctx -> users(ctx, connectionPool));
    }

    public static void orders(Context ctx, ConnectionPool connectionPool){
        AdminController adminController = new AdminController();
        try {
            adminController.giveOrdersToHTML(connectionPool, ctx); // Pass users to HTML
            ctx.render("admin_order.html"); // Render the page
        } catch (SQLException e) {
            ctx.attribute("message", "Error fetching users: " + e.getMessage());
            ctx.render("error.html"); // Render an error page if needed
        }
    }

    public static void users(Context ctx, ConnectionPool connectionPool) {
        AdminController adminController = new AdminController();
        try {
            adminController.giveUserToHTML(connectionPool, ctx); // Pass users to HTML
            ctx.render("admin_customer.html"); // Render the page
        } catch (SQLException e) {
            ctx.attribute("message", "Error fetching users: " + e.getMessage());
            ctx.render("error.html"); // Render an error page if needed
        }
    }


    public void giveOrdersToHTML(ConnectionPool connectionPool, Context ctx) throws SQLException {
        //We take our orders from the database and make them usable on html that use the key orders. This ${orders}
        AdminMapper admin_Mapper = new AdminMapper();
        List<OrdersAndUsers> orders = admin_Mapper.getOrdersAndUsers(connectionPool);
        ctx.attribute("orders", orders);
    }

    public void giveUserToHTML(ConnectionPool connectionPool, Context ctx) throws SQLException {
        //We take our users from the database and make them usable on html that use the key users. This $(users)
        AdminMapper admin_Mapper = new AdminMapper();
        List<User> users = admin_Mapper.getAllUsers(connectionPool);
        ctx.attribute("users", users);
    }
}
