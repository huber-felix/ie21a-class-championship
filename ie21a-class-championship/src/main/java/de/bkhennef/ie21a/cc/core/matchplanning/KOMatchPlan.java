package de.bkhennef.ie21a.cc.core.matchplanning;

import java.util.ArrayList;
import java.util.List;
import de.bkhennef.ie21a.cc.core.entities.Game;
import de.bkhennef.ie21a.cc.core.entities.Match;
import de.bkhennef.ie21a.cc.core.entities.MatchStatus;
import de.bkhennef.ie21a.cc.core.entities.Player;

public class KOMatchPlan implements MatchPlan {
    
    private List<KORound> rounds;
    
    public KOMatchPlan(List<Player> players, Game game, boolean isOfficial) {
        rounds.add(new KORound(players, game, isOfficial));
    }

    @Override
    public List<Match> getMatches() {
        List<Match> result = new ArrayList<>();
            for(KORound round : rounds) {
                result.addAll(round.getMatches());
            }
            return result;
    }

    /*
     * @returns a value between 0 and 1 representing the ration of finished matches
     */
    @Override
    public double getProgress() {
        return getMatches().size() / getMatches().stream()
            .filter(m -> (m.getStatus() == MatchStatus.NOT_STARTED || m.getStatus() == MatchStatus.STARTED))
            .count();
    }

    List<KORound> getRounds() {
        return rounds;
    }


}
