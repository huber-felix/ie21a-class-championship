package de.bkhennef.ie21a.cc.core.matchplanning;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import de.bkhennef.ie21a.cc.core.entities.Game;
import de.bkhennef.ie21a.cc.core.entities.Match;
import de.bkhennef.ie21a.cc.core.entities.Player;

public class FFOMatchPlanTest {

    @Test
    public void test() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("p1"));
        players.add(new Player("p2"));
        players.add(new Player("p3"));
        players.add(new Player("p4"));
        players.add(new Player("p5"));
        players.add(new Player("p6"));
        players.add(new Player("p7"));
        players.add(new Player("p8"));
        FFOMatchPlan plan = new FFOMatchPlan(new Game("Moorhuhn", 2, 2), players, 4);
        System.out.println("matches:" + plan.getMatches());
        for (Match match : plan.getMatches()) {
            System.out.println(match.getPlayers());
        }
    }

}
