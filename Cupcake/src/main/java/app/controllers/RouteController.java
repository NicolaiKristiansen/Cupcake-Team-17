package app.controllers;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class RouteController {
    //Class created by Nicolai

    private static CupcakeController cupcakeController = new CupcakeController();

    public static void routes(Javalin app, ConnectionPool connectionPool) {
        //Function by Nicolai
        //When we go to a new path it goes through here
        app.get("/home", ctx -> homePage(ctx, connectionPool));
        app.get("/admin_index", ctx -> admin_index(ctx, connectionPool) );

    }

    public static void homePage(Context ctx, ConnectionPool connectionPool) {
        //Function made by Nicolai
        //Renders the html Homepage.html and uses the giveCupcakeOptionsToHTML function to give the dropdown bars options
        cupcakeController.giveCupcakeOptionsToHTML(connectionPool, ctx);
        ctx.render("home.html");
    }

    public static void admin_index(Context ctx, ConnectionPool connectionPool) {
        ctx.render("admin_index.html");
    }
}
