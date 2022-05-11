package com.mcplugin.cdelc.lockout.tasks.kill;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDeathEvent;

import javax.swing.text.html.parser.Entity;

public class KillTask extends Task {

    EntityType killTarget;

    Sheep s;

    public KillTask(GameInstance instance, EntityType targetType) {
        super(instance);
        killTarget = targetType;
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EntityDeathEvent){
            EntityDeathEvent event = (EntityDeathEvent) e;
            LivingEntity target = event.getEntity();
            Player killer = target.getKiller();
            if(killer != null && target.getType() == killTarget){
                this.complete(killer);
            }
        }
    }

    @Override
    public String getKeyword() {
        return "kill" + killTarget.name();
    }

    @Override
    public String getDescription() {
        return "Kill a " + killTarget.name();
    }
}
