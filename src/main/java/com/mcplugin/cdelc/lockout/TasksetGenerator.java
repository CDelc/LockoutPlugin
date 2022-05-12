package com.mcplugin.cdelc.lockout;

import com.mcplugin.cdelc.lockout.tasks.Task;
import com.mcplugin.cdelc.lockout.tasks.kill.DrownZombie;
import com.mcplugin.cdelc.lockout.tasks.kill.KillMobEndCrystal;
import com.mcplugin.cdelc.lockout.tasks.kill.KillTask;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.entity.EntityDamageEvent;
import org.checkerframework.checker.units.qual.K;

import java.util.ArrayList;

public class TasksetGenerator {

    GameInstance game;

    public TasksetGenerator(GameInstance instance){
        game = instance;
    }

    public void populateTaskset(ArrayList<Task> tasks){

        tasks.clear();
        getKillTasks(tasks);

    }

    private void getKillTasks(ArrayList<Task> tasks){
        tasks.add(new KillTask(game, EntityType.WITHER_SKELETON, 1));
        tasks.add(new KillTask(game, EntityType.WANDERING_TRADER, 3));
        tasks.add(new KillTask(game, EntityType.BLAZE, 1));
        tasks.add(new KillTask(game, EntityType.ENDERMAN, 0));

        tasks.add(new KillTask(game, EntityType.CREEPER, 0, EntityDamageEvent.DamageCause.FIRE));

        tasks.add(new KillMobEndCrystal(game, 2));
        tasks.add(new DrownZombie(game, 1));

    }

}
