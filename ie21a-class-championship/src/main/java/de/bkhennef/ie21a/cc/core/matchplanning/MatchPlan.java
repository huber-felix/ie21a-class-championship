package de.bkhennef.ie21a.cc.core.matchplanning;

import java.util.List;
import de.bkhennef.ie21a.cc.core.entities.Match;

public interface MatchPlan {

    public List<Match> getMatches();

    public Match getNextMatch();

    public List<Match> getStartedMatches();

    public List<Match> getFinishedMatches();

    public List<Match> getNotStartedMatches();

}
