package com.mcplugin.cdelc.lockout.gui;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Collection;

public class LockoutScoreboard {

    GameInstance instance;
    Player owner;

    Scoreboard scoreboard;
    TaskSidebar sidebar;
    Objective tasksCompleted;

    public LockoutScoreboard(GameInstance instance, Player owner) {
        this.instance = instance;
        this.owner = owner;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.sidebar = new TaskSidebar(this, instance.getTasks());
        this.tasksCompleted = scoreboard.registerNewObjective(
                "tasksCompleted", "dummy", "Tasks Completed"
        );
        tasksCompleted.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        tasksCompleted.setDisplaySlot(DisplaySlot.BELOW_NAME);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public Player getOwner() {
        return owner;
    }

    public void onTaskComplete(Task task, Player whoCompleted) {
        sidebar.onTaskComplete(task, whoCompleted);
        tasksCompleted.getScore(whoCompleted.getName()).setScore(instance.getNumTasksCompleted(whoCompleted));
    }

    public void show() {
        owner.setScoreboard(scoreboard);
    }

    public void addPlayer(Player p) {
        Team playerTeam = scoreboard.registerNewTeam(p.getName());
        playerTeam.addEntry(p.getName());
        tasksCompleted.getScore(p.getName()).setScore(instance.getNumTasksCompleted(p));
    }

    public void removePlayer(Player p) {
        Team playerTeam = scoreboard.getEntryTeam(p.getName());
        if (playerTeam != null) playerTeam.unregister();
    }
}
