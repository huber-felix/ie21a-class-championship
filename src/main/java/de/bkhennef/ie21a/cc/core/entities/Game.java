package de.bkhennef.ie21a.cc.core.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 
 * Represents a specific game title like FIFA or Mario Kart not the individual matches
 * 
 * All fields are private and there are only the instances constructed statically in this class
 */
public class Game {

    public String name;

    public long minPlayers;

    public long maxPlayers;


    public Game(){

    }

    public Game(String name, long minPlayers, long maxPlayers) {
        Objects.requireNonNull(name);
        if (name.isBlank() || name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be blank.");
        }
        this.name = name.strip();
        if (!(minPlayers >= 1)) {
            throw new IllegalArgumentException("Mininum Number of Players must be <=1.");
        }
        this.minPlayers = minPlayers;
        if (!(maxPlayers >= 1)) {
            throw new IllegalArgumentException("Maximum Number of Players must be <=1.");
        }
        this.maxPlayers = maxPlayers;
    }

}
