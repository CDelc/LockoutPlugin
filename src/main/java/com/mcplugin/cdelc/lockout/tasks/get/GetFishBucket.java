package com.mcplugin.cdelc.lockout.tasks.get;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.Material;
import org.bukkit.entity.Fish;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerBucketEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class GetFishBucket extends Task {
    public GetFishBucket(GameInstance instance, int diff) {
        super(instance, diff);
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof PlayerBucketEntityEvent){
            if(((PlayerBucketEntityEvent) e).getEntity() instanceof Fish){
                this.complete(((PlayerBucketEntityEvent) e).getPlayer());
            }
        }
    }

    @Override
    public String getKeyword() {
        return "getFishBucket";
    }

    @Override
    public String getDescription() {
        return "Get a fish in a bucket";
    }
}
