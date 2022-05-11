package com.mcplugin.cdelc.lockout.tasks;

import com.mcplugin.cdelc.lockout.GameInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public abstract class Task implements Listener {

    GameInstance parentGame;

    public Task(GameInstance instance){
        parentGame = instance;
    }

    public abstract void onEvent(Event e);

    protected void complete(Player p){
        parentGame.completeTask(this, p);
    }

    @EventHandler
    public void onAdvancementCompletion(PlayerAdvancementDoneEvent e){
        onEvent(e);
    }


}
