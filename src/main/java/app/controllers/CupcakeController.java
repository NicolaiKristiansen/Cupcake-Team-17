package app.controllers;

import app.entities.Cupcake;
import app.persistence.ConnectionPool;
import app.persistence.CupcakeMapper;
import io.javalin.http.Context;
import java.util.List;

public class CupcakeController {
    //Class made by Nicolai


    //Function made by Nicolai
    //This function will get a list of cupcakes from our database then give context that can send it to a html page
    //Just call this function before rendering the page
    public void giveCupcakeOptionsToHTML(ConnectionPool connectionPool, Context ctx) {
        CupcakeMapper cupcake_Mapper = new CupcakeMapper();
        List<Cupcake> cupcakes = cupcake_Mapper.getCupcakeOptions(connectionPool);
        ctx.attribute("cupcakes", cupcakes);
    }
}
