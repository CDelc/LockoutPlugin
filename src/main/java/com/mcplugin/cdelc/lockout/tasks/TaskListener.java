package com.mcplugin.cdelc.lockout.tasks;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.*;

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

    @EventHandler
    public void onCraftItemEvent(CraftItemEvent e) {
        game.sendEventToTasks(e);
    }

    @EventHandler
    public void onBreed(EntityBreedEvent e){
        game.sendEventToTasks(e);
    }

    @EventHandler
    void onEntitySpawn(EntitySpawnEvent e){
        game.sendEventToTasks(e);
    }

    @EventHandler
    public void onEffect(EntityPotionEffectEvent e){
        game.sendEventToTasks(e);
    }

    @EventHandler
    public void onPlayerStatisticIncrement(PlayerStatisticIncrementEvent e) { game.sendEventToTasks(e); }

    @EventHandler
    public void onEntityTransform(EntityTransformEvent e){
        game.sendEventToTasks(e);
    }

    @EventHandler
    public void onBucketEntity(PlayerBucketEntityEvent e){
        game.sendEventToTasks(e);
    }

    @EventHandler
    public void onBucket(PlayerBucketFillEvent e){
        game.sendEventToTasks(e);
    }

    @EventHandler
    public void onXpChange(PlayerLevelChangeEvent e){
        game.sendEventToTasks(e);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        game.sendEventToTasks(e);
    }

}
