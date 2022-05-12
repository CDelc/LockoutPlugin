package com.mcplugin.cdelc.lockout;

import com.mcplugin.cdelc.lockout.tasks.Task;
import com.mcplugin.cdelc.lockout.tasks.kill.KillTask;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WitherSkeleton;
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
        tasks.add(new KillTask(game, EntityType.WITHER_SKELETON, 2));
        tasks.add(new KillTask(game, EntityType.WANDERING_TRADER, 4));

    }

}
