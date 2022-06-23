package de.bkhennef.ie21a;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.bkhennef.ie21a.cc.core.entities.Badge;
import de.bkhennef.ie21a.cc.core.entities.Game;
import de.bkhennef.ie21a.cc.core.entities.Match;
import de.bkhennef.ie21a.cc.core.entities.Player;
import de.bkhennef.ie21a.cc.database.DataRoot;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;



/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args )
    {
        //INITIATING dataRoot and storage        
        DataRoot dataRoot;
        EmbeddedStorageManager storage = EmbeddedStorage.start();
        if (storage.root() == null) {
            dataRoot = new DataRoot(storage);
            storage.setRoot(dataRoot);
            System.out.println("Setting fresh root");
        } else {
            dataRoot = (DataRoot) storage.root();
            System.out.println("Root was loaded");
        }

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


        Match match1 = new Match(dataRoot.getPlayers(), dataRoot.getGames().get(0), true, 1);
        dataRoot.addMatch(match1);


        app.post("/newPlayer/", ctx -> {
            Player player = mapper.readValue(ctx.body(), Player.class);
            dataRoot.addPlayer(player);
            ctx.json(ctx.body());
        });


        app.get("/players", ctx -> {
            String json = mapper.writeValueAsString(dataRoot.getPlayers());
            ctx.json(json);
        });


        app.get("/games", ctx -> {
            String json = mapper.writeValueAsString(dataRoot.getGames());
            ctx.json(json);
        });

        app.get("/matches", ctx -> {
            String json = mapper.writeValueAsString(dataRoot.getMatches());
            ctx.json(json);
        });


        app.post("/newMatch", ctx -> {
            Match match = mapper.readValue(ctx.body(), Match.class);
            dataRoot.addMatch(match);
            ctx.json(ctx.body());
        });

        //Shutting down the database at JVM shutdown
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Storage shutting down");
                storage.shutdown();
            }
        });

    }
}
