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
        app.get("/Homepage", ctx -> homePage(ctx, connectionPool));

    }

    public static void homePage(Context ctx, ConnectionPool connectionPool) {
        //Function made by Nicolai
        //Renders the html Homepage.html and uses the giveCupcakeOptionsToHTML function to give the dropdown bars options
        cupcakeController.giveCupcakeOptionsToHTML(connectionPool, ctx);
        ctx.render("Homepage.html");
    }
}
