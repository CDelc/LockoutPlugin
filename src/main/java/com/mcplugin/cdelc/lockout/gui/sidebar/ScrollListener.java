package com.mcplugin.cdelc.lockout.gui.sidebar;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class ScrollListener implements Listener {

    private static ScrollListener singleton;

    private Set<Consumer<PlayerItemHeldEvent>> subscribers;

    public ScrollListener() {
        subscribers = new HashSet<>();
    }

    public static ScrollListener singleton() {
        if (singleton == null) singleton = new ScrollListener();
        return singleton;
    }

    public void register(Consumer<PlayerItemHeldEvent> subscriber) {
        subscribers.add(subscriber);
    }

    public void unregister(Consumer<PlayerItemHeldEvent> subscriber) {
        subscribers.remove(subscriber);
    }

    @EventHandler
    public void onScroll(PlayerItemHeldEvent e) {
        for (Consumer<PlayerItemHeldEvent> subscriber : subscribers) {
            subscriber.accept(e);
        }
    }
}
