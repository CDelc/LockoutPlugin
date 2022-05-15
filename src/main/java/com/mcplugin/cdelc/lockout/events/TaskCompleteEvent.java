package com.mcplugin.cdelc.lockout.events;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class TaskCompleteEvent extends LockoutEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    Task task;
    Player player;

    public TaskCompleteEvent(GameInstance instance, Task task, Player whoCompleted) {
        super(instance);
        this.task = task;
        this.player = whoCompleted;
    }

    public Task getTask() {
        return task;
    }

    public Player getPlayer() {
        return player;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
