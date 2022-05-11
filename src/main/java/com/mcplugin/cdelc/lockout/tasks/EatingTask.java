package com.mcplugin.cdelc.lockout.tasks;

import com.mcplugin.cdelc.lockout.GameInstance;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class EatingTask extends Task {

    ItemStack eatGoal;

    public EatingTask(GameInstance instance, ItemStack eatGoal) {
        super(instance);
        this.eatGoal = eatGoal;
    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof PlayerItemConsumeEvent) {
            PlayerItemConsumeEvent consumeEvent = (PlayerItemConsumeEvent) e;
            if (eatGoal.isSimilar(consumeEvent.getItem())) {
                complete(consumeEvent.getPlayer());
            }
        }
    }
}
