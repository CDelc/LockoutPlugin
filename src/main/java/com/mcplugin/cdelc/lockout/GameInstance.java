package com.mcplugin.cdelc.lockout;

import com.mcplugin.cdelc.lockout.tasks.Task;
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

    HashSet<? extends Task> tasks = new HashSet<Task>();

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

    /**
     * Picks the tasks for the game and adds them to the tasks set.
     */
    public void selectTasks(){

    }

    /**
     * Start the game
     * @return Success/failure
     */
    public boolean start(){



        isRunning = true;
        return true;
    }

    /**
     * Do something once a task is completed by player p
     * @param complete
     * @param p
     */
    public void completeTask(Task complete, Player p){

    }

    public void setNumTasks(int numTasks) {
        this.numTasks = numTasks;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    public void setMinDiff(int minDiff) {
        this.minDiff = minDiff;
    }

    public void setMaxDiff(int maxDiff) {
        this.maxDiff = maxDiff;
    }

    public void setPvp(boolean pvp) {
        this.pvp = pvp;
    }

    public void setCompass(boolean compass) {
        this.compass = compass;
    }

}
