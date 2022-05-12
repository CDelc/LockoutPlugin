package com.mcplugin.cdelc.lockout.tasks.eat;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class EatingTask extends Task {

    ItemStack eatGoal;

    public EatingTask(GameInstance instance, ItemStack eatGoal, int diff) {
        super(instance, diff);
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

    @Override
    public String getKeyword() {
        return "eat" + eatGoal.getType().name();
    }

    @Override
    public String getDescription() {
        return "Eat " + eatGoal.getType().name();
    }
}
