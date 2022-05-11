package com.mcplugin.cdelc.lockout.tasks.breed;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityBreedEvent;

public class BreedTask extends Task {

    EntityType breedTarget;

    public <T extends LivingEntity> BreedTask(GameInstance instance, EntityType targetType) {
        super(instance);
        breedTarget = targetType;
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EntityBreedEvent){
            EntityBreedEvent event = (EntityBreedEvent) e;

            LivingEntity breeder = event.getBreeder();
            if(!(breeder instanceof Player)) return;
            Player player = (Player) breeder;

            LivingEntity newborn = event.getEntity();
            if(newborn.getType() == breedTarget){
                this.complete(player);
            }
        }
    }

    @Override
    public String getKeyword() {
        return "breed" + breedTarget.name();
    }

    @Override
    public String getDescription() {
        return "Breed a " + breedTarget.name();
    }
}
