package com.mcplugin.cdelc.lockout;

import com.mcplugin.cdelc.lockout.tasks.Task;
import com.mcplugin.cdelc.lockout.tasks.get.GetTask;
import com.mcplugin.cdelc.lockout.tasks.kill.DrownZombie;
import com.mcplugin.cdelc.lockout.tasks.kill.KillMobEndCrystal;
import com.mcplugin.cdelc.lockout.tasks.kill.KillTask;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.K;

import java.util.ArrayList;
import java.util.Random;

public class TasksetGenerator implements Runnable{

    GameInstance game;
    Random rng = new Random();

    public TasksetGenerator(GameInstance instance){
        game = instance;
    }

    public void populateTaskset(ArrayList<Task> tasks){

        tasks.clear();
        getKillTasks(tasks);
        getCollectTasks(tasks);

    }

    private void getKillTasks(ArrayList<Task> tasks){
        addToSet(new KillTask(game, EntityType.WITHER_SKELETON, 1), tasks);
        addToSet(new KillTask(game, EntityType.WANDERING_TRADER, 3), tasks);
        addToSet(new KillTask(game, EntityType.BLAZE, 1), tasks);
        addToSet(new KillTask(game, EntityType.ENDERMAN, 0), tasks);

        addToSet(new KillTask(game, EntityType.CREEPER, 0, EntityDamageEvent.DamageCause.FIRE), tasks);

        addToSet(new KillMobEndCrystal(game, 2), tasks);
        addToSet(new DrownZombie(game, 1), tasks);

    }

    private void getCollectTasks(ArrayList<Task> tasks){
        addToSet(new GetTask(game, new ItemStack(Material.COBBLESTONE, rng.nextInt(32) + 32), 0), tasks);
    }

    public void addToSet(Task t, ArrayList<Task> list){
        if(t.getDifficulty() <= game.getMaxDiff() && t.getDifficulty() >= game.getMinDiff()) {
            list.add(t);
        }
    }

    @Override
    public void run() {

    }
}
