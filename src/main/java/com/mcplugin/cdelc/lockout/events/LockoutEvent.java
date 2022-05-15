package com.mcplugin.cdelc.lockout.events;

import com.mcplugin.cdelc.lockout.GameInstance;
import org.bukkit.event.Event;

public abstract class LockoutEvent extends Event {

    GameInstance instance;

    public LockoutEvent(GameInstance instance) {
        this.instance = instance;
    }

    public GameInstance getLockout() {
        return instance;
    }
}
