package com.mcplugin.cdelc.lockout.events;

import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.entity.Player;

public class TaskCompleteEvent implements LockoutEvent {
    Task task;
    Player player;

    public TaskCompleteEvent(Task task, Player whoCompleted) {
        this.task = task;
        this.player = whoCompleted;
    }

    public Task getTask() {
        return task;
    }

    public Player getPlayer() {
        return player;
    }
}
