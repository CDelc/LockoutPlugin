package com.mcplugin.cdelc.lockout.tasks.craft;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftTask extends Task {

    ItemStack craftGoal;

    public CraftTask(GameInstance game, ItemStack craftGoal) {
        super(game);
        this.craftGoal = craftGoal;
    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof CraftItemEvent) {
            CraftItemEvent craftEvent = (CraftItemEvent) e;
            if (craftGoal.isSimilar(craftEvent.getRecipe().getResult())) {
                complete((Player) craftEvent.getWhoClicked());
            }
        }
    }
}
