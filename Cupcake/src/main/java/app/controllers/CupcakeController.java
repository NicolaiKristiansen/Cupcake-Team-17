package app.controllers;

import app.entities.CupcakeBottom;
import app.entities.CupcakeTop;
import app.entities.Orderline;
import app.persistence.BasketMapper;
import app.persistence.ConnectionPool;
import app.persistence.CupcakeMapper;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class CupcakeController {


    //This function will get a list of cupcakes from our database then give context that can send it to a html page
    //Just call this function before rendering the page
    public void giveCupcakeTopOptionsToHTML(ConnectionPool connectionPool, Context ctx) {
        CupcakeMapper cupcake_Mapper = new CupcakeMapper();
        List<CupcakeTop> cupcakeTops = cupcake_Mapper.getCupcakeTopOptions(connectionPool);
        ctx.attribute("cupcakeTops", cupcakeTops);
    }

    //This function will get a list of cupcakes from our database then give context that can send it to a html page
    //Just call this function before rendering the page
    public void giveCupcakeBottomOptionsToHTML(ConnectionPool connectionPool, Context ctx) {
        CupcakeMapper cupcake_Mapper = new CupcakeMapper();
        List<CupcakeBottom> cupcakeBottoms = cupcake_Mapper.getCupcakeBottomOptions(connectionPool);
        ctx.attribute("cupcakeBottoms", cupcakeBottoms);
    }

    public void giveOrderlinesToHTML(ConnectionPool connectionPool, Context ctx) {
        BasketMapper basketMapper = new BasketMapper();
        List<Orderline> orderlines = basketMapper.getOrderlinesForBasket(ctx);
        ctx.attribute("orderlines", orderlines);

    }
}
