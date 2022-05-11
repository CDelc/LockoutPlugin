package com.mcplugin.cdelc.lockout.tasks;

import com.mcplugin.cdelc.lockout.GameInstance;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDeathEvent;

import javax.swing.text.html.parser.Entity;

public class KillTask extends Task {

    Class killTarget;

    Sheep s;

    public KillTask(GameInstance instance, Class type) {
        super(instance);
        killTarget = type;
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EntityDeathEvent){
            EntityDeathEvent event = (EntityDeathEvent) e;
            LivingEntity target = event.getEntity();
            Player killer = target.getKiller();
            if(killer != null && target.getClass().isInstance(killTarget)){
                this.complete(killer);
            }
        }
    }
}
