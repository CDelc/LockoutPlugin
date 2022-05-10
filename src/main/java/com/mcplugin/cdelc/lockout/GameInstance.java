package com.mcplugin.cdelc.lockout;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashSet;

public class GameInstance  {

    HashSet<Player> players;

    boolean isRunning;

    int numPlayers;
    int numTasks;
    int borderSize;
    int minDiff;
    int maxDiff;
    boolean pvp;
    boolean compass;

    public GameInstance(){
        isRunning = false;
        players = new HashSet<Player>();
        numPlayers = players.size();
    }

    public void addPlayer(Player p){
        if(!isRunning) players.add(p);
        numPlayers = players.size();
    }

    public boolean removePlayer(Player p){
        boolean rc = players.remove(p);
        numPlayers = players.size();
        if(!rc) return false;
        else return true;
    }

}
