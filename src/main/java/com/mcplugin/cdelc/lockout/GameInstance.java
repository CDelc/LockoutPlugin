package com.mcplugin.cdelc.lockout;

import com.mcplugin.cdelc.lockout.events.EventRegistry;
import com.mcplugin.cdelc.lockout.events.TaskCompleteEvent;
import com.mcplugin.cdelc.lockout.gui.LockoutGUI;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GameInstance  {

    HashSet<Player> players;
    HashMap<Player, Integer> playerTaskCounts;
    LockoutGUI gui;
    EventRegistry lockoutEvents;

    boolean isRunning;

    int numPlayers;
    int numTasks;
    int borderSize;
    int minDiff;
    int maxDiff;
    boolean pvp;
    boolean compass;

    HashSet<Task> selectedTasks = new HashSet<Task>();
    ArrayList<Task> allTasks = new ArrayList<Task>();

    public GameInstance(){
        isRunning = false;
        players = new HashSet<Player>();
        numPlayers = players.size();
        TasksetGenerator taskGetter = new TasksetGenerator(this);
        taskGetter.populateTaskset(allTasks);
        playerTaskCounts = new HashMap<>();
        gui = new LockoutGUI(this);
        lockoutEvents = new EventRegistry();
        lockoutEvents.register(gui);
    }


    public void addPlayer(Player p){
        if(!isRunning) {
            players.add(p);
            playerTaskCounts.put(p, 0);
            gui.addPlayer(p);
            numPlayers = players.size();
        }

    }

    public boolean removePlayer(Player p){
        boolean rc = players.remove(p);
        playerTaskCounts.remove(p);
        numPlayers = players.size();
        if(!rc) return false;
        else return true;
    }

    public void sendEventToTasks(Event e){
        for(Task t : selectedTasks) t.onEvent(e);
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
        gui.show();
        return true;
    }

    /**
     * Do something once a task is completed by player p
     * @param complete
     * @param p
     */
    public void completeTask(Task complete, Player p){
        playerTaskCounts.put(p, playerTaskCounts.get(p) + 1);
        lockoutEvents.raise(new TaskCompleteEvent(complete, p));
    }

    public Set<Task> getTasks() { return this.selectedTasks; }

    public Set<Player> getPlayers() { return this.players; }

    public int getNumTasksCompleted(Player p) {
        return playerTaskCounts.get(p);
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
