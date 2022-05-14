package com.mcplugin.cdelc.lockout.events;

import com.mcplugin.cdelc.lockout.GameInstance;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class EventRegistry {

    Set<Consumer<LockoutEvent>> subscribers;

    public EventRegistry() {
        subscribers = new HashSet<>();
    }

    public void raise(LockoutEvent e) {
        for (Consumer<LockoutEvent> subscriber : subscribers) {
            subscriber.accept(e);
        }
    }

    public void register(Consumer<LockoutEvent> subscriber) {
        subscribers.add(subscriber);
    }

    public void unregister(Consumer<LockoutEvent> subscriber) {
        subscribers.remove(subscriber);
    }
}
