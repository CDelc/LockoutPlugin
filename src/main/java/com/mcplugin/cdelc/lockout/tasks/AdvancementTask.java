package com.mcplugin.cdelc.lockout.tasks;

import com.mcplugin.cdelc.lockout.GameInstance;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class AdvancementTask extends Task {

    String targetAdvancement;

    public AdvancementTask(GameInstance instance, String advancementKey) {
        super(instance);
        targetAdvancement = advancementKey;
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof PlayerAdvancementDoneEvent){
            PlayerAdvancementDoneEvent event = (PlayerAdvancementDoneEvent) e;

            Advancement advancement = event.getAdvancement();
            Player completor = event.getPlayer();
            if(advancement.getKey().getKey().equals(targetAdvancement)) {
                this.complete(completor);
            }
        }
    }
}
