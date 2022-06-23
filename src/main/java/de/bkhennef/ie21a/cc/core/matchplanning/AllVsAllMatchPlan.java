package de.bkhennef.ie21a.cc.core.matchplanning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import de.bkhennef.ie21a.cc.core.entities.Game;
import de.bkhennef.ie21a.cc.core.entities.Match;
import de.bkhennef.ie21a.cc.core.entities.Player;

public class AllVsAllMatchPlan implements MatchPlan {
    
    Game game;

    List<Match> matches = new ArrayList<>();
    
    public AllVsAllMatchPlan(Game game, List<Player> players) {
        this.game = game;
        Collections.shuffle(players);
        generateMatches(matches, players);
    }
    
    
    private List<Match> generateMatches(List<Match> matches, List<Player> remainingPlayers) {
        Player toMatchWithTheRest = remainingPlayers.get(0);
        remainingPlayers.remove(toMatchWithTheRest);
        for(Player p : remainingPlayers) {
            matches.add(new Match(List.of(toMatchWithTheRest, p), game));
        }
        if(remainingPlayers.isEmpty()) {
            return matches;
        }
        return generateMatches(matches, remainingPlayers);
    }

    @Override
    public List<Match> getMatches() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Method getMatches() not implemented yet");
    }

    @Override
    public double getProgress() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Method getProgress() not implemented yet");
    }

}
