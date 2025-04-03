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
        app.get("adminorder", ctx -> orders(ctx, connectionPool));
        app.get("user", ctx -> users(ctx, connectionPool));
        app.post("deleteorder", ctx -> deleteOrder(ctx, connectionPool));
        app.post("updatemoney", ctx -> updateMoney(ctx, connectionPool));
    }



    public static void orders(Context ctx, ConnectionPool connectionPool){
        AdminController adminController = new AdminController();
        try {
            adminController.giveOrdersToHTML(connectionPool, ctx);
            ctx.render("admin_order.html");
        } catch (SQLException e) {
            ctx.attribute("message", "Error fetching users: " + e.getMessage());
            ctx.render("error.html");
        }
    }

    public static void users(Context ctx, ConnectionPool connectionPool) {
        AdminController adminController = new AdminController();
        try {
            adminController.giveUserToHTML(connectionPool, ctx);
            ctx.render("admin_customer.html");
        } catch (SQLException e) {
            ctx.attribute("message", "Error fetching users: " + e.getMessage());
            ctx.render("error.html");
        }
    }

    public void giveOrdersToHTML(ConnectionPool connectionPool, Context ctx) throws SQLException {
        //We take our orders from the database and make them usable on html that use the key orders. This ${orders}
        List<OrdersAndUsers> orders = AdminMapper.getOrdersAndUsers(connectionPool);
        ctx.attribute("orders", orders);
    }

    public void giveUserToHTML(ConnectionPool connectionPool, Context ctx) throws SQLException {
        //We take our users from the database and make them usable on html that use the key users. This $(users)
        List<User> users = AdminMapper.getAllUsersWithoutAdmin(connectionPool);
        ctx.attribute("users", users);
    }


    private static void updateMoney(Context ctx, ConnectionPool connectionPool) {
        try {
            int userId = Integer.parseInt(ctx.formParam("userId"));
            float money = Float.parseFloat(ctx.formParam("money"));
            AdminMapper.updateUsersAllowance(userId, money, connectionPool);
            List<User> users = AdminMapper.getAllUsersWithoutAdmin(connectionPool);
            ctx.attribute("users", users);
            ctx.render("admin_customer.html");
        } catch (NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("admin_customer.html");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteOrder(Context ctx, ConnectionPool connectionPool) {
        try {
            int orderId = Integer.parseInt(ctx.formParam("orderId"));
            System.out.println(orderId);
            AdminMapper.deleteOrder(orderId, connectionPool);
            List<Order> orders = AdminMapper.getOrders(connectionPool);
            ctx.attribute("orders", orders);
            ctx.redirect("/order");
        } catch (NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
