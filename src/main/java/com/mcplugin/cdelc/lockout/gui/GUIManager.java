package com.mcplugin.cdelc.lockout.gui;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.events.LockoutStartEvent;
import com.mcplugin.cdelc.lockout.events.LockoutStopEvent;
import com.mcplugin.cdelc.lockout.events.TaskCompleteEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class GUIManager implements Consumer<Event> {
    GameInstance instance;
    Map<UUID, LockoutScoreboard> scoreboards;

    public GUIManager(GameInstance instance) {
        this.instance = instance;
        this.scoreboards = new HashMap<>();
        GUIListener.singleton().register(this);
    }

    public GUIManager(GameInstance instance, Collection<Player> players) {
        this(instance);
        players.forEach(this::addPlayer);
    }

    public void addPlayer(Player p) {
        if (scoreboards.containsKey(p.getUniqueId())) return;
        LockoutScoreboard scoreboard = new LockoutScoreboard(instance, p);
        for (Player player : instance.getPlayers()) scoreboard.addPlayer(player);
        for(LockoutScoreboard s : scoreboards.values()) s.addPlayer(p);
        scoreboards.put(p.getUniqueId(), scoreboard);
    }

    public void removePlayer(Player p) {
        LockoutScoreboard sc = scoreboards.get(p.getUniqueId());
        sc.unregister();
        scoreboards.remove(p.getUniqueId());
        scoreboards.values().forEach(scoreboard -> scoreboard.removePlayer(p));
    }

    /**
     * Shows all managed scoreboards
     */
    public void showAll() {
        for(LockoutScoreboard s : scoreboards.values()) s.show();
    }

    /**
     * Hides all managed scoreboards
     */
    public void hideAll() {
        for (LockoutScoreboard s : scoreboards.values()) s.hide();
    }

    @Override
    public void accept(Event event) {
        if (event instanceof PlayerToggleSneakEvent) {
            onToggleCrouch((PlayerToggleSneakEvent) event);
        }
        else if (event instanceof PlayerJoinEvent) {
            onPlayerJoin((PlayerJoinEvent) event);
        }
        else if (event instanceof TaskCompleteEvent) {
            onTaskComplete((TaskCompleteEvent) event);
        }
        else if (event instanceof LockoutStartEvent) {
            onLockoutStart((LockoutStartEvent) event);
        }
        else if (event instanceof LockoutStopEvent) {
            onLockoutStop((LockoutStopEvent) event);
        }
    }

    public void onPlayerJoin(PlayerJoinEvent e) {
        if (scoreboards.containsKey(e.getPlayer().getUniqueId()) && instance.running()) {
            LockoutScoreboard sc = scoreboards.get(e.getPlayer().getUniqueId());
            sc.show();
        }
    }

    public void onTaskComplete(TaskCompleteEvent e) {
        scoreboards.values().forEach(scoreboard -> scoreboard.onTaskComplete(e.getTask(), e.getPlayer()));
    }

    public void onLockoutStart(LockoutStartEvent e) {
        for (LockoutScoreboard s : scoreboards.values()) s.registerTasks(instance.getTasks());
        showAll();
    }

    public void onLockoutStop(LockoutStopEvent e) {
        hideAll();
        for (LockoutScoreboard s : scoreboards.values()) s.clear();
    }

    public void onToggleCrouch(PlayerToggleSneakEvent e) {
        UUID uid = e.getPlayer().getUniqueId();
        if (e.isSneaking() && scoreboards.containsKey(uid) && instance.running()) {
            LockoutScoreboard sc = scoreboards.get(uid);
            if (sc.isVisible()) {
                sc.hide();
            } else {
                sc.show();
            }
        }
    }

    /**
     * Unregisters this and all managed scoreboards from the GUIListener
     */
    public void unregister() {
        hideAll();
        GUIListener.singleton().unregister(this);
        scoreboards.values().forEach(LockoutScoreboard::unregister);
    }
}
