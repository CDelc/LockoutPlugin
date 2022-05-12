package com.mcplugin.cdelc.lockout.tasks.kill;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import javax.swing.text.html.parser.Entity;

public class KillTask extends Task {

    EntityType killTarget;
    EntityDamageEvent.DamageCause desiredCause;

    Sheep s;

    public KillTask(GameInstance instance, EntityType targetType, int diff) {
        super(instance);
        killTarget = targetType;
        difficulty = diff;
        desiredCause = null;
    }

    public KillTask(GameInstance instance, EntityType targetType, int diff, EntityDamageEvent.DamageCause deathCause) {
        super(instance);
        killTarget = targetType;
        difficulty = diff;
        desiredCause = deathCause;
    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof EntityDeathEvent) {
            EntityDeathEvent event = (EntityDeathEvent) e;
            LivingEntity target = event.getEntity();
            EntityDamageEvent deathCause = target.getLastDamageCause();
            Player killer = target.getKiller();

            if (killer != null && target.getType() == killTarget &&
                    (desiredCause == null || deathCause.equals(desiredCause))) {
                this.complete(killer);
            }
        }
    }

    @Override
    public String getKeyword() {
        if(desiredCause == null) return "kill" + killTarget.name();
        else return "kill" + killTarget.name() + desiredCause.name();
    }

    @Override
    public String getDescription() {
        if(desiredCause == null) return "Kill a " + killTarget.name();
        else return "Kill a " + killTarget.name() + " with " + desiredCause.name();
    }

}
