package com.mcplugin.cdelc.lockout.tasks.kill;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityTransformEvent;

public class DrownZombie extends Task {


    public DrownZombie(GameInstance instance, int diff) {
        super(instance, diff);
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EntityTransformEvent){
            EntityTransformEvent event = (EntityTransformEvent) e;
            if(event.getTransformedEntity().getType().equals(EntityType.DROWNED) && event.getTransformReason().equals(EntityTransformEvent.TransformReason.DROWNED)){
                Player closest = KillTask.getClosestPlayer(event.getEntity(), 30, 40, 30);
                if(closest != null) this.complete(closest);
            }
        }
    }

    @Override
    public String getKeyword() {
        return "drownZombie";
    }

    @Override
    public String getDescription() {
        return "Be near a zombie when it turns into a drowned";
    }
}
