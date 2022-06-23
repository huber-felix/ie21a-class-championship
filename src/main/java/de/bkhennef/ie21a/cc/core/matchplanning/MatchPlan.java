package de.bkhennef.ie21a.cc.core.matchplanning;

import java.util.List;
import de.bkhennef.ie21a.cc.core.entities.Game;
import de.bkhennef.ie21a.cc.core.entities.Match;
import de.bkhennef.ie21a.cc.core.entities.MatchStatus;

public interface MatchPlan {

    List<Match> getMatches();

    Game getGame();

    /*
     * @returns a value between 0 and 1 representing the ration of finished matches
     */

    default double getProgress() {
        return getMatches().size() / getMatches().stream()
            .filter(m -> (m.getStatus() == MatchStatus.NOT_STARTED || m.getStatus() == MatchStatus.STARTED))
            .count();
    }

}
