package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

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
    public void onEntityPickupItemEvent(EntityPickupItemEvent e) {
        game.sendEventToTasks(e);
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent e) {
        game.sendEventToTasks(e);
    }

    @EventHandler
    public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent e) {
        game.sendEventToTasks(e);
    }

}
