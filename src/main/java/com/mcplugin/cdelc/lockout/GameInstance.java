package com.mcplugin.cdelc.lockout;

import com.mcplugin.cdelc.lockout.events.EventRegistry;
import com.mcplugin.cdelc.lockout.events.TaskCompleteEvent;
import com.mcplugin.cdelc.lockout.gui.GUIManager;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class GameInstance  {

    HashSet<Player> players;
    HashMap<Player, Integer> playerTaskCounts;
    GUIManager gui;
    EventRegistry lockoutEvents;

    boolean isRunning;

    int numPlayers;
    int numTasks;
    int borderSize;
    int minDiff;
    int maxDiff;
    boolean pvp;
    boolean compass;

    TasksetGenerator taskGetter;

    HashSet<Task> selectedTasks = new HashSet<Task>();
    ArrayList<Task> allTasks = new ArrayList<Task>();

    public GameInstance(){
        isRunning = false;
        players = new HashSet<Player>();
        numPlayers = players.size();
        taskGetter = new TasksetGenerator(this);
        numTasks = 25;
        minDiff = 0;
        maxDiff = 4;
        pvp = true;
        compass = true;
        borderSize = 2000;

        taskGetter.populateTaskset(allTasks);
        playerTaskCounts = new HashMap<>();
        gui = new GUIManager(this);
        lockoutEvents = new EventRegistry();
        lockoutEvents.register(gui);
    }

    public void clear(){
        allTasks.clear();
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
        for(Task t : allTasks) selectedTasks.add(t);
        allTasks.clear();
    }

    /**
     * Start the game
     * @return Success/failure
     */
    public boolean start(){

        selectTasks();

        Random rng = new Random();

        HashSet<World> worlds = new HashSet<>();
        for(Player p : players){
            int x = rng.nextInt(4000) - 2000;
            int z = rng.nextInt(4000) - 2000;
            Location tmp = new Location(p.getLocation().getWorld(), x, p.getLocation().getWorld().getHighestBlockAt(x, z).getY() + 1, z);
            //p.teleport(tmp);
            p.setBedSpawnLocation(tmp);
            worlds.add(p.getLocation().getWorld());
            p.getInventory().clear(); //Clear inventory
            p.setLevel(0); p.setExp(0); //Clear xp
            p.setHealth(20); //Heal
            p.setFoodLevel(20); //Saturate
            for(PotionEffect eff : p.getActivePotionEffects()) p.removePotionEffect(eff.getType()); //Remove all potion effects

        }

        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            if(!players.contains(p) && worlds.contains(p.getLocation().getWorld())) p.setGameMode(GameMode.SPECTATOR);
        }

        isRunning = true;
        gui.show();
        return true;
    }

    public boolean stop(){
        isRunning = false;
        selectedTasks.clear();
        for(Player p : Bukkit.getServer().getOnlinePlayers()) p.setGameMode(GameMode.SURVIVAL);
        taskGetter.populateTaskset(allTasks);
        return true;
    }

    /**
     * Do something once a task is completed by player p
     * @param complete
     * @param p
     */
    public void completeTask(Task complete, Player p){
        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            player.sendMessage(ChatColor.AQUA + p.getDisplayName() + ChatColor.WHITE + " has completed " + ChatColor.YELLOW + complete.getDescription());
        }
        playerTaskCounts.put(p, playerTaskCounts.get(p) + 1);
        lockoutEvents.raise(new TaskCompleteEvent(complete, p));
    }

    public Set<Task> getTasks() { return this.selectedTasks; }

    public Set<Player> getPlayers() { return this.players; }

    public int getNumTasksCompleted(Player p) {
        return playerTaskCounts.get(p);
    }

    public int getMaxTasks(){
        return allTasks.size();
    }

    public void setNumTasks(int numTasks) {
        this.numTasks = numTasks;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    public void setMinDiff(int minDiff) {
        this.minDiff = minDiff;
        taskGetter = new TasksetGenerator(this);
    }

    public int getMinDiff(){
        return minDiff;
    }

    public int getMaxDiff(){
        return maxDiff;
    }

    public void setMaxDiff(int maxDiff) {
        this.maxDiff = maxDiff;
        taskGetter = new TasksetGenerator(this);
    }

    public void setPvp(boolean pvp) {
        this.pvp = pvp;
    }

    public void setCompass(boolean compass) {
        this.compass = compass;
    }

    public boolean running(){
        return isRunning;
    }

    public String settingsToString(){
        String output = "";
        output += (ChatColor.AQUA + "Lockout Settings\n---------------------------------------\n");
        output += (ChatColor.AQUA + "Players:");
        for(Player p : players) output += (" " + ChatColor.YELLOW + p.getName());
        output += "\n";
        output += (ChatColor.AQUA + "Number of players: " + ChatColor.YELLOW + numPlayers + "\n");
        output += (ChatColor.AQUA + "Number of tasks: " + ChatColor.YELLOW + numTasks + "\n");
        output += (ChatColor.AQUA + "Minimum task difficulty: " + ChatColor.YELLOW + minDiff + "\n");
        output += (ChatColor.AQUA + "Maximum task difficulty: " + ChatColor.YELLOW + maxDiff + "\n");
        output += (ChatColor.AQUA + "Border size: " + ChatColor.YELLOW + borderSize + "\n");
        output += (ChatColor.AQUA + "PvP enabled: " + ChatColor.YELLOW + pvp + "\n");
        output += (ChatColor.AQUA + "Compass enabled: " + ChatColor.YELLOW + compass + "\n");

        return output;
    }

}
