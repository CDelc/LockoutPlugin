package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class TaskListener implements Listener {

    GameInstance game;

    public TaskListener(Lockout plugin){
        game = plugin.getGame();
    }

    @EventHandler
    public void onAdvancementCompletion(PlayerAdvancementDoneEvent e){
        game.sendEventToTasks(e);
    }

}
