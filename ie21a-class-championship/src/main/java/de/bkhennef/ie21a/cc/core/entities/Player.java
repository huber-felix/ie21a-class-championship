package de.bkhennef.ie21a.cc.core.entities;

import java.util.List;
import java.util.Objects;

public class Player {

    private final String name;
    private List<Match> matches;
    private List<Badge> badges;

    public Player(String name) {
        Objects.requireNonNull(name);
        if (name.isBlank() || name.isEmpty()) {
            throw new IllegalArgumentException("Player name must contain characters");
        }
        this.name = name.strip();
    }

    public String getName() {
        return name;
    }

    public long totalWins() {
        long wins = 0;
        for (Match match : this.matches) {
            if (match.getWinners().contains(this)) {
                wins++;
            }
        }
        return wins;
    }

    public long winsInGame(Game game) {
        long wins = 0;
        for (Match match : this.matches) {
            if (match.getGame().equals(game) && match.getWinners().contains(this)) {
                wins++;
            }
        }
        return wins;
    }

    public void honor(Badge badge) {
        this.badges.add(badge);
    }
}
