package com.mcplugin.cdelc.lockout.tasks.block;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakTask extends Task {

    Material block;

    public BlockBreakTask(GameInstance instance, Material type) {
        super(instance);
        block = type;
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof BlockBreakEvent){
            BlockBreakEvent event = (BlockBreakEvent) e;
            Block target = event.getBlock();
            Player breaker = event.getPlayer();

            if(target.getType().equals(block)){
                complete(breaker);
            }

        }
    }
}
