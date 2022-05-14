package com.mcplugin.cdelc.lockout;

import com.mcplugin.cdelc.lockout.tasks.SleepTask;
import com.mcplugin.cdelc.lockout.tasks.Task;
import com.mcplugin.cdelc.lockout.tasks.breed.GeneralBreedTask;
import com.mcplugin.cdelc.lockout.tasks.get.GetFishBucket;
import com.mcplugin.cdelc.lockout.tasks.get.GetMultipleTask;
import com.mcplugin.cdelc.lockout.tasks.get.GetTask;
import com.mcplugin.cdelc.lockout.tasks.get.GetXpLevel;
import com.mcplugin.cdelc.lockout.tasks.kill.DrownZombie;
import com.mcplugin.cdelc.lockout.tasks.kill.KillMobEndCrystal;
import com.mcplugin.cdelc.lockout.tasks.kill.KillTask;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.K;

import java.util.*;

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

        addToSet(new SleepTask(game, 0), tasks);

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
        addToSet(new GetTask(game, new ItemStack(Material.COBBLESTONE, rng.nextInt(33) + 32), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.DIRT, rng.nextInt(33) + 32), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.OAK_PLANKS, rng.nextInt(33) + 32), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.OAK_LOG, rng.nextInt(11) + 5), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.REDSTONE, 1), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.LAVA_BUCKET, 1), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.MILK_BUCKET, 1), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.WATER_BUCKET, 1), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.ANDESITE, rng.nextInt(17) + 16), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.DIORITE, rng.nextInt(17) + 16), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.GRANITE, rng.nextInt(17) + 16), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.IRON_BLOCK, 1), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.NOTE_BLOCK, rng.nextInt(4) + 2), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.OAK_LEAVES, rng.nextInt(33) + 32), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.SNOWBALL, rng.nextInt(9) + 8), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.DIAMOND, 1), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.ROTTEN_FLESH, rng.nextInt(11) + 5), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.STONE, rng.nextInt(23) + 10), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.BREAD, rng.nextInt(4) + 2), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.HAY_BLOCK, 1), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.FLOWER_POT, 1), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.FEATHER, rng.nextInt(9) + 2), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.OAK_WOOD, rng.nextInt(11) + 5), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.CHARCOAL, rng.nextInt(11) + 5), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.COAL, rng.nextInt(11) + 5), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.FISHING_ROD, 1), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.APPLE, 1), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.STICK, rng.nextInt(33) + 32), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.KELP, rng.nextInt(33) + 32), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.STRIPPED_OAK_LOG, rng.nextInt(33) + 32), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.COD, rng.nextInt(4) + 2), 0), tasks);
        addToSet(new GetTask(game, new ItemStack(Material.SALMON, rng.nextInt(2) + 2), 0), tasks);

        EnumSet<Material> materials = EnumSet.allOf(Material.class);
        HashSet<ItemStack> wool = new HashSet<>();
        for(Material m : materials) if(m.name().contains("WOOL")) wool.add(new ItemStack(m, 1));
        HashSet<ItemStack> stairs = new HashSet<>();
        for(Material m : materials) if(m.name().contains("STAIRS")) stairs.add(new ItemStack(m, 1));
        HashSet<ItemStack> slab = new HashSet<>();
        for(Material m : materials) if(m.name().contains("SLAB")) slab.add(new ItemStack(m, 1));
        HashSet<ItemStack> edible = new HashSet<>();
        for(Material m : materials) if(m.isEdible()) edible.add(new ItemStack(m, 1));

        addToSet(new GetMultipleTask(game, new ItemStack[]
                {new ItemStack(Material.POPPY, rng.nextInt(21) + 5),
                        new ItemStack(Material.DANDELION, rng.nextInt(21) + 5)}, 2, 0, "getPoppyDandelion", null), tasks);
        int tmp = rng.nextInt(3) + 2;
        addToSet(new GetMultipleTask(game, wool.toArray(new ItemStack[]{}), tmp, 0, "getWoolColors", "Get " + tmp + " different colors of wool"), tasks);
        tmp = rng.nextInt(3) + 2;
        addToSet(new GetMultipleTask(game, (ItemStack[]) stairs.toArray(new ItemStack[]{}), tmp, 0, "getDiffStairs", "Get " + tmp + " different types of stairs"), tasks);
        tmp = rng.nextInt(3) + 2;
        addToSet(new GetMultipleTask(game, (ItemStack[]) slab.toArray(new ItemStack[]{}), tmp, 0, "getDiffSlabs", "Get " + tmp + " different types of slabs"), tasks);
        tmp = rng.nextInt(4) + 6;
        addToSet(new GetMultipleTask(game, (ItemStack[]) edible.toArray(new ItemStack[]{}), tmp, 0, "getEdibles", "Get " + tmp + " different edible items"), tasks);

        addToSet(new GetFishBucket(game, 0), tasks);
        addToSet(new GetXpLevel(game, rng.nextInt(3) + 5, 0), tasks);
    }

    private void getBreedTasks(ArrayList<Task> tasks){
        addToSet(new GeneralBreedTask(game, 0), tasks);
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
