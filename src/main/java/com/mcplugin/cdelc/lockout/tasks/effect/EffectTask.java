package com.mcplugin.cdelc.lockout.tasks.effect;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;

public class EffectTask extends Task {

    PotionEffect effect;

    public EffectTask(GameInstance instance, PotionEffect goal) {
        super(instance);
        effect = goal;
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EntityPotionEffectEvent){
            EntityPotionEffectEvent event = (EntityPotionEffectEvent) e;

            Entity target = event.getEntity();
            if(!(target instanceof Player)) return;
            Player player = (Player) target;

            if(!event.getAction().equals(EntityPotionEffectEvent.Action.ADDED)) return;
            if(event.getNewEffect().getType().equals(effect.getType())){
                complete(player);
            }
        }
    }
}
