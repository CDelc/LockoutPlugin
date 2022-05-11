package com.mcplugin.cdelc.lockout.tasks;

import com.mcplugin.cdelc.lockout.GameInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public abstract class Task implements Listener {

    GameInstance parentGame;
    boolean isComplete;

    public Task(GameInstance instance){
        parentGame = instance;
        isComplete = false;
    }

    public abstract void onEvent(Event e);

    protected void complete(Player p){
        if(isComplete) return;
        isComplete = true;
        parentGame.completeTask(this, p);
    }




}
