package de.bkhennef.ie21a.cc.core.matchplanning;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import de.bkhennef.ie21a.cc.core.entities.Game;
import de.bkhennef.ie21a.cc.core.entities.Match;
import de.bkhennef.ie21a.cc.core.entities.Player;

/*
 * This match plan takes a game, a list of players and a number of rounds and 
 * generates ${rounds} number of matches where all the given players are participants
 */
public class FFOMatchPlan implements MatchPlan {

    private List<Match> matches;

    private Game game;

    private List<Player> players;

    public FFOMatchPlan(Game game, List<Player> players, long rounds) {
        Objects.requireNonNull(game);
        this.game = game;
        Objects.requireNonNull(players);
        if (players.isEmpty()) {
            throw new IllegalArgumentException("There have to be at least two players");
        }
        this.players = players;
        this.matches = generateMatches(players, rounds);
    }

    private List<Match> generateMatches(List<Player> players2, long rounds) {
        List<Match> result = new ArrayList<>();
        for (int i = 0; i < rounds; i++) {
            result.add(new Match(players2, game));
        }
        return result;
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
