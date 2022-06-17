package de.bkhennef.ie21a.cc.core.entities;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Player {

    private long id;

    private final String name;

    private transient List<Match> matches = new ArrayList<>();

    private List<Badge> badges = new ArrayList<>();

    private long totalWins;


    public Player(String name, long id) {
        this.id = id;
        Objects.requireNonNull(name);
        if (name.isBlank() || name.isEmpty()) {
            throw new IllegalArgumentException("Player name must contain characters");
        }
        this.name = name.strip();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public long getTotalWins() {
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
