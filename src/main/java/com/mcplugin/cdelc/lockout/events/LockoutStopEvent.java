package com.mcplugin.cdelc.lockout.events;

import com.mcplugin.cdelc.lockout.GameInstance;
import org.bukkit.event.HandlerList;

public class LockoutStopEvent extends LockoutEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public LockoutStopEvent(GameInstance instance) {
        super(instance);
    }
}
