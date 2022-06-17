package de.bkhennef.ie21a.cc.core.matchplanning;

import java.util.List;
import de.bkhennef.ie21a.cc.core.entities.Match;

public interface MatchPlan {

    public List<Match> getMatches();

    double getProgress();

}
