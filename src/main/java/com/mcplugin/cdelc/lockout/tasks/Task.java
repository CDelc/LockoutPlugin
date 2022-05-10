package com.mcplugin.cdelc.lockout.tasks;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public abstract class Task implements Listener {

    public abstract void onEvent(Event e);

    @EventHandler
    public void onAdvancementCompletion(PlayerAdvancementDoneEvent e){
        onEvent(e);
    }


}
