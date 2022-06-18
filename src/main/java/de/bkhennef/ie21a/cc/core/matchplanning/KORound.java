package de.bkhennef.ie21a.cc.core.matchplanning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import de.bkhennef.ie21a.cc.core.entities.Game;
import de.bkhennef.ie21a.cc.core.entities.Match;
import de.bkhennef.ie21a.cc.core.entities.Player;

public class KORound {

    private List<Match> matches = new ArrayList<>();

    public KORound(List<Player> players, Game game, boolean isOfficial) {
        Collections.shuffle(players);
        for (int i = 0; i < players.size(); i += 2) {
            matches.add(new Match(List.of(players.get(i), players.get(i + 1)), game, isOfficial, 0));
        }
    }

    public List<Match> getMatches() {
        return matches;
    }

}
