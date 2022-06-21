package de.bkhennef.ie21a;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import de.bkhennef.ie21a.cc.core.entities.Badge;
import de.bkhennef.ie21a.cc.core.entities.Game;
import de.bkhennef.ie21a.cc.core.entities.Match;
import de.bkhennef.ie21a.cc.core.entities.Player;
import de.bkhennef.ie21a.cc.database.DataRoot;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static DataRoot dataRoot = new DataRoot();

    public static void main( String[] args )
    {

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

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

        dataRoot.addGame(new Game("FIFA", 1, 7));
        dataRoot.addGame(new Game("Mario Kart", 1, 8));
        dataRoot.addGame(new Game("Minecraft", 1, 8));


        app.post("/newPlayer/", ctx -> {
            Player player = new Gson().fromJson(ctx.body(), Player.class);
            dataRoot.addPlayer(player);
            ctx.json(ctx.body());
        });


        app.get("/players", ctx -> {
            String json = new Gson().toJson(dataRoot.getPlayers());
            ctx.json(json);
        });


        app.get("/games", ctx -> {
            String json = new Gson().toJson(dataRoot.getGames());
            ctx.json(json);
        });



        app.post("/newMatch", ctx -> {


           // Match match = new Match(dataRoot.getPlayers(), dataRoot.getGames().get(0), true, 1);
          //  ctx.json(match);

            Match match = new Gson().fromJson(ctx.body(), Match.class);
            dataRoot.addMatch(match);
            ctx.json(ctx.body());
        });


    }
}
