package de.bkhennef.ie21a;

import com.google.gson.Gson;
import de.bkhennef.ie21a.cc.core.entities.Badge;
import de.bkhennef.ie21a.cc.core.entities.Player;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Javalin app = Javalin.create(config -> {config.addStaticFiles("/public", Location.CLASSPATH);}).start(7777);

        List<Player> playerObjectList = new ArrayList<>();
        Player player1 = new Player("Spieler 1");
        Player player2 = new Player("Spieler 2");
        player2.getBadges().add(Badge.BEGINNERS_LUCK);
        player1.getBadges().add(Badge.LOOSER);
        playerObjectList.add(player1);
        playerObjectList.add(player2);

        app.get("/players", ctx -> {
            String json = new Gson().toJson(playerObjectList);
            ctx.html(json);
        });
    }
}
