package com.mcplugin.cdelc.lockout.tasks.breed;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityBreedEvent;

public class GeneralBreedTask extends Task {
    public GeneralBreedTask(GameInstance instance, int diff) {
        super(instance, diff);
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EntityBreedEvent){
            EntityBreedEvent event = (EntityBreedEvent) e;
            LivingEntity breeder = event.getBreeder();
            if(!(breeder instanceof Player)) return;
            this.complete((Player) breeder);
        }
    }

    @Override
    public String getKeyword() {
        return "BreedMobs";
    }

    @Override
    public String getDescription() {
        return "Breed any set of mobs";
    }
}
