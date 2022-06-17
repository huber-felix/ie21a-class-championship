package de.bkhennef.ie21a.cc.core.entities;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Match {

    private long id;
    private List<Player> winners = new ArrayList<>();

    private final Game game;

    private final List<Player> players = new ArrayList<>();

    private MatchStatus status;

    private boolean official;
    private Instant startTime = Instant.now();
    private Instant finishTime = Instant.now();

    private String cancelledReason;

    public Match(List<Player> players, Game game, boolean official, long id) {
        this.id = id;
        this.status = MatchStatus.NOT_STARTED;
        Objects.requireNonNull(game);
        this.game = game;
        if (players.isEmpty()) {
            throw new IllegalArgumentException("There is no match if there are no players");
        }
        winners = new ArrayList<>();
        this.official = official;
    }

    public long getId() {
        return id;
    }

    public List<Player> getWinners() {
        return (winners);
    }

    public Game getGame() {
        return this.game;
    }

    public void start() {
        if(status != MatchStatus.NOT_STARTED) {
            throw new UnsupportedOperationException("Couldn't start game because it's" + status.toString());
        }
        status = MatchStatus.STARTED;
        startTime = Instant.now();
    }

    public void finish(List<Player> winners) {
        if (status != MatchStatus.NOT_STARTED) {
            throw new UnsupportedOperationException("Can't finish a " + status.toString() + " match.");
        }
        if (!players.containsAll(winners)) {
            throw new IllegalArgumentException("At least one of the given winners was not even playing.");
        }
        status = MatchStatus.STARTED;
        finishTime = Instant.now();
    }

    public void cancel(String reason) {
        cancelledReason = reason;
        this.status = MatchStatus.CANCELLED;
    }

    public Duration getDuration() {
        if (status != MatchStatus.FINISHED) {
            return Duration.between(startTime, Instant.now());
        }
        return Duration.between(startTime, finishTime);
    }

    public MatchStatus getStatus() {
        return status;
    }

    public String getCancelledReason() {
        return cancelledReason;
    }

    public boolean isOfficial() {
        return official;
    }

}
