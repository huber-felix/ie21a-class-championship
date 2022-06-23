package de.bkhennef.ie21a.cc.core.matchplanning;

import java.util.ArrayList;
import java.util.List;
import de.bkhennef.ie21a.cc.core.entities.Game;
import de.bkhennef.ie21a.cc.core.entities.Match;
import de.bkhennef.ie21a.cc.core.entities.Player;

public class KOMatchPlan implements MatchPlan {
    
    private List<KORound> rounds = new ArrayList<>();
    private Game game;
    
    public KOMatchPlan(List<Player> players, Game game, boolean isOfficial) {
        rounds.add(new KORound(players, game, isOfficial));
        this.game = game;
    }

    @Override
    public List<Match> getMatches() {
        List<Match> result = new ArrayList<>();
        for(KORound round : rounds) {
            result.addAll(round.getMatches());
        }
        return result;
    }



    List<KORound> getRounds() {
        return rounds;
    }

    @Override
    public Game getGame() {
        return game;
    }

}
