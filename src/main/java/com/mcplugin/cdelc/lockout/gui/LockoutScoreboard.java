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
    Scoreboard fallbackScoreboard;
    TaskSidebar sidebar;
    Objective tasksCompleted;

    public LockoutScoreboard(GameInstance instance, Player owner) {
        this.instance = instance;
        this.ownerUUID = owner.getUniqueId();
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.fallbackScoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        this.sidebar = new TaskSidebar(this);
        this.tasksCompleted = scoreboard.registerNewObjective(
                "tasksCompleted", "dummy", "Tasks Completed"
        );
        tasksCompleted.setDisplaySlot(DisplaySlot.PLAYER_LIST);

        sidebar.show();
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public Player getOwner() {
        return Bukkit.getPlayer(ownerUUID);
    }

    /**
     * Gets whether the scoreboard is being shown to its owner
     * @return
     */
    public boolean isVisible() {
        return getOwner().getScoreboard().equals(scoreboard);
    }

    /**
     * Sets the scoreboard that will be fallen back on when this scoreboard is hidden.
     *
     * Set to Bukkit.getScoreboardMangaer().getMainScoreboard() by default
     * @param scoreboard
     */
    public void setFallbackScoreboard(Scoreboard scoreboard) {
        fallbackScoreboard = scoreboard;
    }

    /**
     * Updates sidebar display and player score
     * @param task
     * @param whoCompleted
     */
    public void onTaskComplete(Task task, Player whoCompleted) {
        sidebar.onTaskComplete(task, whoCompleted);
        tasksCompleted.getScore(whoCompleted.getName()).setScore(instance.getNumTasksCompleted(whoCompleted));
    }

    /**
     * Shows this scoreboard to the owner
     */
    public void show() {
        getOwner().setScoreboard(scoreboard);
    }

    /**
     * Hides this scoreboard from the owner
     */
    public void hide() {
        getOwner().setScoreboard(fallbackScoreboard);
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

    /**
     * Register a set of
     * @param tasks
     */
    public void registerTasks(Collection<Task> tasks) {
        sidebar.registerTasks(tasks);
    }

    /**
     * Unregisters this scoreboard's resources
     */
    public void unregister() {
        hide();
        sidebar.unregister();
    }

    /**
     * Clears all tracked information about tasks
     */
    public void clear() {
        for (String entry : scoreboard.getEntries()) {
            if (tasksCompleted.getScore(entry).isScoreSet()) {
                tasksCompleted.getScore(entry).setScore(0);
            }
        }
        sidebar.clear();
    }
}
