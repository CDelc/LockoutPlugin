package com.mcplugin.cdelc.lockout.tasks;

import com.mcplugin.cdelc.lockout.GameInstance;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.EnumSet;
import java.util.HashSet;

public class SleepTask extends Task {
    public SleepTask(GameInstance instance, int diff) {
        super(instance, diff);
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof PlayerInteractEvent){
            PlayerInteractEvent event = (PlayerInteractEvent) e;
            Player p = event.getPlayer();
            if(event.getClickedBlock() == null) return;
            if(event.getClickedBlock().getBlockData() instanceof Bed && p.getWorld().getEnvironment().equals(World.Environment.NORMAL) &&
                    (p.getWorld().getTime() >= 12300 || p.getWorld().getTime() == 0)) complete(p);
        }
    }

    @Override
    public String getKeyword() {
        return "sleep";
    }

    @Override
    public String getDescription() {
        return "Sleep in a bed";
    }
}
