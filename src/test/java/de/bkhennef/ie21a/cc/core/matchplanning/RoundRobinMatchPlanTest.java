package de.bkhennef.ie21a.cc.core.matchplanning;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import de.bkhennef.ie21a.cc.core.entities.Game;
import de.bkhennef.ie21a.cc.core.entities.Match;
import de.bkhennef.ie21a.cc.core.entities.Player;

public class RoundRobinMatchPlanTest {

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
        RoundRobinMatchPlan plan = new RoundRobinMatchPlan(new Game("Moorhuhn", 2, 2), players);
        for (Match match : plan.matches) {
            System.out.println(match.getPlayers());
        }
        assertEquals(7 + 6 + 5 + 4 + 3 + 2 + 1, plan.matches.size());
    }

}
