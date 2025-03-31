package app.controllers;

import app.entities.Order;
import app.entities.Orderline;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.OrderMapper;
import app.persistence.OrderlineMapper;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.List;


public class UserController {

    static User user;
    private static CupcakeController cupcakeController = new CupcakeController();
    private static OrderMapper orderMapper = new OrderMapper();
    private static OrderlineMapper orderlineMapper = new OrderlineMapper();




    public static void addRoutes(Javalin app, ConnectionPool connectionPool){
        app.post("login",ctx -> login(ctx, connectionPool));
        app.get("logout", ctx -> logout(ctx));
        app.get("createuser", ctx -> ctx.render("createuser.html"));
        app.post("createuser", ctx -> createUser(ctx, connectionPool));

        app.get("addtobasket", ctx -> ctx.render("basket.html"));
        //app.post("addtobasket", ctx -> basket(ctx, connectionPool));

        app.get("order", ctx -> ctx.render("admin_order.html"));
        app.get("savedOrders", ctx -> savedOrdersPage(ctx, connectionPool));
    }

    public static void basket(Context ctx, ConnectionPool connectionPool){
        ctx.render("basket.html");
    }

    public static void savedOrder(Context ctx, ConnectionPool connectionPool){
        ctx.render("admin_order.html");
    }

    private static void createUser(Context ctx, ConnectionPool connectionPool) {
        //Hent form parametre
        String email = ctx.formParam("email");
        String password1 = ctx.formParam("password1");
        String password2 = ctx.formParam("password2");

        if (password1.equals(password2)) {
            try {
                UserMapper.createuser(email, password1, connectionPool);
                ctx.attribute("message", "Du er hermed oprettet med brugernavn" + email + "." +
                        " Nu skal du logge på.");
                ctx.render("index.html");
            } catch (DatabaseException e) {
                ctx.attribute("message", "Dit brugernavn findes allerede, Prøv igen, eller log ind");
                ctx.render("createuser.html");
            }

        } else {
            ctx.attribute("message", "Dine to passwords matcher ikke! Prøv igen");
            ctx.render("createuser.html");
        }
    }
    public static void logout(Context ctx) {
        ctx.req().getSession().invalidate();
        ctx.redirect("/");
    }

    public static void login(Context ctx, ConnectionPool connectionPool) {
        // Hent form parametre
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        // Check om bruger findes i databasen med de angivende username + password
        try {
            user = UserMapper.login(email, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);
            // Hvis ja, send videre til task side
            //ctx.attribute("taskList",  taskList);
            cupcakeController.giveCupcakeTopOptionsToHTML(connectionPool, ctx);
            cupcakeController.giveCupcakeBottomOptionsToHTML(connectionPool, ctx);
            ctx.render("home.html");
        }catch (DatabaseException e) {
            // Hvis nej, send tilbage til login side med fejl
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        }
    }

    public static void savedOrdersPage(Context ctx, ConnectionPool connectionPool) throws SQLException {
        // Retrieve all orders for the current user
        orderMapper.setOrdersForUser(user, connectionPool);
        List<Order> savedOrders = user.getOrders();

        // Retrieve all orderlines for the orders
        // Here we get orderlines for all orders at once based on user orders.
        for (Order order : savedOrders) {
            // We get orderlines for each order
            List<Orderline> savedOrderlines = orderlineMapper.getOrderlineByOrderid(user, connectionPool);
            order.setOrderlines(savedOrderlines);  // Assuming Order has a setOrderlines method
        }

        // Set the orders in the context for the Thymeleaf template to access
        ctx.attribute("savedOrders", savedOrders);

        // Render the saved orders page
        ctx.render("saved_orders.html");
    }


}