package com.mcplugin.cdelc.lockout.tasks.kill;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillMobEndCrystal extends Task {


    public KillMobEndCrystal(GameInstance instance, int diff) {
        super(instance, diff);
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EntityDeathEvent && ((EntityDeathEvent) e).getEntity() instanceof Mob){
            EntityDeathEvent event = (EntityDeathEvent) e;
            if(!(event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent)) return;
            if(((EntityDamageByEntityEvent) event.getEntity().getLastDamageCause()).getDamager() instanceof EnderCrystal){
                EnderCrystal crystal = (EnderCrystal)((EntityDamageByEntityEvent) event.getEntity().getLastDamageCause()).getDamager();
                if(((EntityDamageByEntityEvent) crystal.getLastDamageCause()).getDamager() instanceof Player){
                    this.complete((Player) ((EntityDamageByEntityEvent) crystal.getLastDamageCause()).getDamager());
                }
                else if(((EntityDamageByEntityEvent) crystal.getLastDamageCause()).getDamager() instanceof Arrow){
                    Arrow arrow = (Arrow) ((EntityDamageByEntityEvent) crystal.getLastDamageCause()).getDamager();
                    if(arrow.getShooter() instanceof Player){
                        this.complete((Player) arrow.getShooter());
                    }
                }
            }
        }
    }

    @Override
    public String getKeyword() {
        return "killMobEndCrystal";
    }

    @Override
    public String getDescription() {
        return "Kill a mob with an end crystal";
    }
}
