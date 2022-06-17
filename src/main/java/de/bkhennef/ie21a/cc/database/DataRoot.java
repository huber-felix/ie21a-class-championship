package de.bkhennef.ie21a.cc.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import de.bkhennef.ie21a.cc.core.entities.Game;
import de.bkhennef.ie21a.cc.core.entities.Match;
import de.bkhennef.ie21a.cc.core.entities.Player;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;

public class DataRoot {

    EmbeddedStorageManager storage;

    public DataRoot() {
        storage = EmbeddedStorage.start(this);
        this.players = new ArrayList<>();
        this.matches = new ArrayList<>();
        this.games = new ArrayList<>();
    }

    private List<Player> players;

    private List<Match> matches;

    private List<Game> games;

    public List<Player> getPlayers() {
        return players;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public List<Game> getGames() {
        return games;
    }

    public void addMatch(Match match) {
        try {
            Objects.requireNonNull(match);
            matches.add(match);
        } catch (NullPointerException e) {
            System.out.println("Match could not be added. Was null. " + e);
        }
        storage.store(matches);
    }

    public void addGame(Game game) {
        try {
            Objects.requireNonNull(game);
            if (games.contains(game)) {
                System.out.println("Game could not be added. Already exists");
            }
            games.add(game);
        } catch (NullPointerException e) {
            System.out.println("Game could not be added. Was null. " + e);
        }
        storage.store(matches);
    }

    public void addPlayer(Player player) {
        try {
            Objects.requireNonNull(player);
            players.add(player);
        } catch (NullPointerException e) {
            System.out.println("Player could not be added. Was null. " + e);
        }
        storage.store(games);
    }
}
