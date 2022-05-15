package com.mcplugin.cdelc.lockout.gui;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Collection;
import java.util.UUID;

public class LockoutScoreboard {

    GameInstance instance;
    UUID ownerUUID;

    Scoreboard scoreboard;
    TaskSidebar sidebar;
    Objective tasksCompleted;
    boolean isVisible;

    public LockoutScoreboard(GameInstance instance, Player owner) {
        this.instance = instance;
        this.ownerUUID = owner.getUniqueId();
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.sidebar = new TaskSidebar(this);
        this.tasksCompleted = scoreboard.registerNewObjective(
                "tasksCompleted", "dummy", "Tasks Completed"
        );
        tasksCompleted.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        isVisible = false;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public Player getOwner() {
        return Bukkit.getPlayer(ownerUUID);
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void onTaskComplete(Task task, Player whoCompleted) {
        sidebar.onTaskComplete(task, whoCompleted);
        tasksCompleted.getScore(whoCompleted.getName()).setScore(instance.getNumTasksCompleted(whoCompleted));
    }

    public void show() {
        getOwner().setScoreboard(scoreboard);
        isVisible = true;
    }

    public void hide(Scoreboard replacement) {
        getOwner().setScoreboard(replacement);
        isVisible = false;
    }

    public void hide() {
        hide(Bukkit.getScoreboardManager().getMainScoreboard());
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

    public void registerTasks(Collection<Task> tasks) {
        sidebar.registerTasks(tasks);
    }

    public void unregister() {
        hide();
        sidebar.unregister();
    }
}
