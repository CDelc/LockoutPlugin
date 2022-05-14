package com.mcplugin.cdelc.lockout.tasks.kill;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;
import java.util.Locale;

public class KillTask extends Task {

    EntityType killTarget;
    EntityDamageEvent.DamageCause desiredCause;

    Sheep s;

    public KillTask(GameInstance instance, EntityType targetType, int diff) {
        super(instance, diff);
        killTarget = targetType;
        desiredCause = null;
    }

    public KillTask(GameInstance instance, EntityType targetType, int diff, EntityDamageEvent.DamageCause deathCause) {
        super(instance, diff);
        killTarget = targetType;
        desiredCause = deathCause;
    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof EntityDeathEvent && desiredCause == null) {
            EntityDeathEvent event = (EntityDeathEvent) e;
            LivingEntity target = event.getEntity();
            Player killer = target.getKiller();

            if (killer != null && target.getType() == killTarget) {
                this.complete(killer);
            }
        }
        else if(e instanceof EntityDeathEvent && desiredCause != EntityDamageEvent.DamageCause.ENTITY_ATTACK){
            EntityDeathEvent event = (EntityDeathEvent) e;
            LivingEntity target = event.getEntity();
            EntityDamageEvent.DamageCause cause = target.getLastDamageCause().getCause();
            if((cause.equals(desiredCause) ||
                    (desiredCause.equals(EntityDamageEvent.DamageCause.FIRE) && cause.equals(EntityDamageEvent.DamageCause.FIRE_TICK)))
                    && target.getType() == killTarget){
                Player closest = getClosestPlayer(target, 30, 20, 30);
                if(closest != null) this.complete(closest);
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
        if(desiredCause == null) return "Kill a " + makeEnumLookBetter(killTarget.name());
        else return "Be near a " + makeEnumLookBetter(killTarget.name()) + " when it dies of " + makeEnumLookBetter(desiredCause.name());
    }



    protected static Player getClosestPlayer(Entity ent, double x, double y, double z){
        List<Entity> closeEntities = ent.getNearbyEntities(x, y, z);
        Location location = ent.getLocation();
        double closest = Double.POSITIVE_INFINITY;
        Player closestPlayer = null;
        for(Entity e : closeEntities){
            if(e instanceof Player && e.getLocation().distanceSquared(location) < closest){
                closestPlayer = (Player) e;
                closest = e.getLocation().distanceSquared(location);
            }
        }
        return closestPlayer;
    }

}
