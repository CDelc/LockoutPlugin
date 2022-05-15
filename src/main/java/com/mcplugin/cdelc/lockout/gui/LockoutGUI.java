package com.mcplugin.cdelc.lockout.gui;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.events.LockoutEventListener;
import com.mcplugin.cdelc.lockout.events.TaskCompleteEvent;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class LockoutGUI implements LockoutEventListener {

    GameInstance instance;
    Set<LockoutScoreboard> scoreboards;

    public LockoutGUI(GameInstance instance) {
        this.instance = instance;
        this.scoreboards = new HashSet<>();
    }

    public LockoutGUI(GameInstance instance, Collection<Player> players) {
        this(instance);
        players.forEach(this::addPlayer);
    }

    public void addPlayer(Player p) {
        scoreboards.add(new LockoutScoreboard(instance, p));
        for(LockoutScoreboard s : scoreboards) s.addPlayer(p);
    }

    public void removePlayer(Player p) {
        scoreboards.forEach(scoreboard -> scoreboard.removePlayer(p));
        scoreboards.removeIf(scoreboard -> scoreboard.getOwner().equals(p));
    }

    @Override
    public void onTaskCompleteEvent(TaskCompleteEvent e) {
        scoreboards.forEach(scoreboard -> scoreboard.onTaskComplete(e.getTask(), e.getPlayer()));
    }

    public void show() {
        for(LockoutScoreboard s : scoreboards) s.show();
    }
}
