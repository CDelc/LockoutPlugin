package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class TaskListener implements Listener {

    GameInstance game;

    public TaskListener(Lockout plugin){
        game = plugin.getGame();
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e){
        game.sendEventToTasks(e);
    }

    @EventHandler
    public void onAdvancementCompletion(PlayerAdvancementDoneEvent e){
        game.sendEventToTasks(e);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        game.sendEventToTasks(e);
    }

}
