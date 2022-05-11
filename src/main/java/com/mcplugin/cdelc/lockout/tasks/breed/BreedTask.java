package com.mcplugin.cdelc.lockout.tasks.breed;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityBreedEvent;

public class BreedTask extends Task {

    LivingEntity breedTarget;

    public <T extends LivingEntity> BreedTask(GameInstance instance, T type) {
        super(instance);
        breedTarget = type;
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EntityBreedEvent){
            EntityBreedEvent event = (EntityBreedEvent) e;

            LivingEntity breeder = event.getBreeder();
            if(!(breeder instanceof Player)) return;
            Player player = (Player) breeder;

            LivingEntity newborn = event.getEntity();
            if(newborn.getClass().isInstance(breedTarget)){
                this.complete(player);
            }
        }
    }
}
