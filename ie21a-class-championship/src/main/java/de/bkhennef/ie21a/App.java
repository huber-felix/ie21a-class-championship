package de.bkhennef.ie21a;

import com.google.gson.Gson;

import de.bkhennef.ie21a.cc.core.entities.Badge;
import de.bkhennef.ie21a.cc.core.entities.Player;
import de.bkhennef.ie21a.cc.database.DataRoot;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

/**
 * Hello world!
 *
 */
public class App 
{
    public static DataRoot dataRoot = new DataRoot();

    public static void main( String[] args )
    {

        Javalin app = Javalin.create(config ->
        {
            config.addStaticFiles("/public", Location.CLASSPATH);
            config.contextPath = "/";
            config.enableDevLogging();
            config.enableCorsForAllOrigins();
        }).start(7777);

        app.before("/*", ctx -> {
            ctx.header("Access-Control-Allow-Origin", "*");
        });

        Player player1 = new Player("Spieler 1", 1);
        Player player2 = new Player("Spieler 2", 2);
        player2.getBadges().add(Badge.BEGINNERS_LUCK);
        player1.getBadges().add(Badge.LOOSER);

        dataRoot.addPlayer(player1);
        dataRoot.addPlayer(player2);

        app.post("/newPlayer/", ctx -> {
            Player player = new Gson().fromJson(ctx.body(), Player.class);
            dataRoot.addPlayer(player);
            ctx.json(ctx.body());
        });


        app.get("/players", ctx -> {
            String json = new Gson().toJson(dataRoot.getPlayers());
            ctx.html(json);
        });




    }
}
