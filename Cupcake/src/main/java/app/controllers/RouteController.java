package app.controllers;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class RouteController {

    private static CupcakeController cupcakeController = new CupcakeController();

    public static void routes(Javalin app, ConnectionPool connectionPool) {
        //When we go to a new path it goes through here
        app.get("/home", ctx -> homePage(ctx, connectionPool));
        app.get("/admin_index", ctx -> admin_index(ctx, connectionPool) );

    }

    public static void homePage(Context ctx, ConnectionPool connectionPool) {
        //Renders the html Homepage.html and uses the giveCupcakeOptionsToHTML function to give the dropdown bars options
        cupcakeController.giveCupcakeTopOptionsToHTML(connectionPool, ctx);
        cupcakeController.giveCupcakeBottomOptionsToHTML(connectionPool, ctx);
        ctx.render("home.html");
    }

    public static void admin_index(Context ctx, ConnectionPool connectionPool) {

        ctx.render("admin_index.html");
    }
}
