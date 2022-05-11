package com.mcplugin.cdelc.lockout.tasks.advance;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class AdvancementTask extends Task {

    String targetAdvancement;
    String advancementDesc;

    public AdvancementTask(GameInstance instance, String advancementKey, String advancementTitle) {
        super(instance);
        targetAdvancement = advancementKey;
        advancementDesc = advancementTitle;
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof PlayerAdvancementDoneEvent){
            PlayerAdvancementDoneEvent event = (PlayerAdvancementDoneEvent) e;

            Advancement advancement = event.getAdvancement();
            Player completer = event.getPlayer();
            if(advancement.getKey().getKey().equals(targetAdvancement)) {
                this.complete(completer);
            }
        }
    }


    @Override
    public String getKeyword() {
        return advancementDesc;
    }

    @Override
    public String getDescription() {
        return "Get the " + advancementDesc + " advancement.";
    }
}
