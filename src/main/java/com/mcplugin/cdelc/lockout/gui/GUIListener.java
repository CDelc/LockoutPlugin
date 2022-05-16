package com.mcplugin.cdelc.lockout.gui;

import com.mcplugin.cdelc.lockout.events.LockoutStartEvent;
import com.mcplugin.cdelc.lockout.events.LockoutStopEvent;
import com.mcplugin.cdelc.lockout.events.TaskCompleteEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class GUIListener implements Listener {

    private static GUIListener singleton;

    private Set<Consumer<Event>> subscribers;

    GUIListener() {
        subscribers = new HashSet<>();
    }

    public static GUIListener singleton() {
        if (singleton == null) singleton = new GUIListener();
        return singleton;
    }

    public void register(Consumer<Event> subscriber) {
        subscribers.add(subscriber);
    }

    public void unregister(Consumer<Event> subscriber) {
        subscribers.remove(subscriber);
    }

    @EventHandler
    public void onScroll(PlayerItemHeldEvent e) {
        raiseGenericEvent(e);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        raiseGenericEvent(e);
    }

    @EventHandler
    public void onTaskComplete(TaskCompleteEvent e) {
        raiseGenericEvent(e);
    }

    @EventHandler
    public void onLockoutStart(LockoutStartEvent e) {
        raiseGenericEvent(e);
    }

    @EventHandler
    public void onLockoutStop(LockoutStopEvent e) {
        raiseGenericEvent(e);
    }

    @EventHandler
    public void onToggleCrouch(PlayerToggleSneakEvent e) {
        raiseGenericEvent(e);
    }

    private void raiseGenericEvent(Event e) {
        for (Consumer<Event> subscriber : subscribers) {
            subscriber.accept(e);
        }
    }
}
