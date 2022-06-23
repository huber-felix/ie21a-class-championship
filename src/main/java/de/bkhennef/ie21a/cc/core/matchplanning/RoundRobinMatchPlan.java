package de.bkhennef.ie21a.cc.core.matchplanning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import de.bkhennef.ie21a.cc.core.entities.Game;
import de.bkhennef.ie21a.cc.core.entities.Match;
import de.bkhennef.ie21a.cc.core.entities.Player;

/*
 * Recursive Implementation of a Round-Robin generation 
 * where every player plays once against every other player
 * 
 * The order of the matches is random
 * 
 */
public class RoundRobinMatchPlan implements MatchPlan {
    
    Game game;

    List<Match> matches = new ArrayList<>();
    
    public RoundRobinMatchPlan(Game game, List<Player> players) {
        this.game = game;
        Collections.shuffle(players);
        generateMatches(matches, players);
        Collections.shuffle(matches);
    }
    
    

    private List<Match> generateMatches(List<Match> matches, List<Player> players) {
        List<Player> remainingPlayers = new ArrayList<>();
        remainingPlayers.addAll(players);
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
        return matches;
    }

    @Override
    public Game getGame() {
        return game;
    }

}
