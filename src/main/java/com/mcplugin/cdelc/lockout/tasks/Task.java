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
    protected int difficulty;

    public Task(GameInstance instance){
        parentGame = instance;
        isComplete = false;
    }

    public abstract void onEvent(Event e);
    public abstract String getKeyword();
    public abstract String getDescription();
    public int getDifficulty(){
        return difficulty;
    }

    protected void complete(Player p){
        if(isComplete) return;
        isComplete = true;
        parentGame.completeTask(this, p);
    }




}
