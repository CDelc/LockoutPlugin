package com.mcplugin.cdelc.lockout;

import com.mcplugin.cdelc.lockout.tasks.Task;
import com.mcplugin.cdelc.lockout.tasks.kill.KillTask;
import org.bukkit.entity.WitherSkeleton;

import java.util.ArrayList;

public class TasksetGenerator {

    GameInstance game;

    public TasksetGenerator(GameInstance instance){
        game = instance;
    }

    public void populateTaskset(ArrayList<Task> tasks){

        tasks.clear();

    }

}
