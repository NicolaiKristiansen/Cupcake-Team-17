package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.Objects;


public class UserController {

    private static CupcakeController cupcakeController = new CupcakeController();




    public static void addRoutes(Javalin app, ConnectionPool connectionPool){
        app.post("login",ctx -> login(ctx, connectionPool));
        app.get("logout", ctx -> logout(ctx));
        app.get("createuser", ctx -> ctx.render("createuser.html"));
        app.post("createuser", ctx -> createUser(ctx, connectionPool));

        app.get("addtobasket", ctx -> ctx.render("basket.html"));
    }

    public static void basket(Context ctx, ConnectionPool connectionPool){
        ctx.render("basket.html");
    }

    public static void savedOrder(Context ctx, ConnectionPool connectionPool){

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
                User user = UserMapper.login(email, password, connectionPool);
                ctx.sessionAttribute("currentUser", user);
                // Hvis ja, send videre til task side
                //ctx.attribute("taskList",  taskList);

                if(user.getRole().equals("user")) {
                    cupcakeController.giveCupcakeTopOptionsToHTML(connectionPool, ctx);
                    cupcakeController.giveCupcakeBottomOptionsToHTML(connectionPool, ctx);
                    ctx.render("home.html");
                } else if (user.getRole().equals("admin")) {
                    ctx.render("admin_index.html");
                } else {
                    System.out.println("Doesn't have a valid role");
                }


            } catch (DatabaseException e) {
                // Hvis nej, send tilbage til login side med fejl
                ctx.attribute("message", e.getMessage());
                ctx.render("index.html");
            }
        }
}