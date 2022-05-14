package com.mcplugin.cdelc.lockout.tasks.get;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerBucketEntityEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class GetTask extends Task {

    ItemStack itemGoal;
    String generalType;

    public GetTask(GameInstance game, ItemStack itemGoal, int diff) {
        super(game, diff);
        this.itemGoal = itemGoal;
        Material mat = itemGoal.getType();
        if(mat.toString().contains("LOG")) generalType = "LOG";
        else if(mat.toString().contains("PLANKS")) generalType = "PLANKS";
        else if(mat.toString().contains("LEAVES")) generalType = "LEAVES";
        else if(mat.toString().contains("WOOD")) generalType = "WOOD";
        else if(mat.toString().contains("STRIPPED")) generalType = "STRIPPED";
        else generalType = null;
    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof InventoryCloseEvent) {
            InventoryCloseEvent closeEvent = (InventoryCloseEvent) e;
            Player player = (Player) closeEvent.getPlayer();
            if (playerInventorySufficient(player, 0)) complete(player);
        }
        else if (e instanceof EntityPickupItemEvent) {
            EntityPickupItemEvent pickupEvent = (EntityPickupItemEvent) e;
            if (pickupEvent.getEntityType() == EntityType.PLAYER) {
                Player player = (Player) pickupEvent.getEntity();
                if(((generalType == null && itemGoal.isSimilar(pickupEvent.getItem().getItemStack())) ||
                        (generalType != null && pickupEvent.getItem().getItemStack().getType().name().contains(generalType))) &&
                        playerInventorySufficient(player, pickupEvent.getItem().getItemStack().getAmount())) {
                    complete(player);
                }
            }
        }
        else if(e instanceof PlayerBucketFillEvent){
            PlayerBucketFillEvent event = (PlayerBucketFillEvent) e;
            Player player = event.getPlayer();
            if(isValidItem(event.getItemStack()) && playerInventorySufficient(player, 1)){
                complete(player);
            }
        }
    }

    @Override
    public String getKeyword() {
        return "get" + itemGoal.getAmount() + "" + itemGoal.getType();
    }

    @Override
    public String getDescription() {
        if(generalType == "PLANKS") return "Get " + itemGoal.getAmount() + " wooden planks";
        else if(generalType == "LOG") return "Get " + itemGoal.getAmount() + " wooden logs";
        else if(generalType == "WOOD") return "Get " + itemGoal.getAmount() + " wood";
        else if(generalType == "STRIPPED") return "Get " + itemGoal.getAmount() + " stripped logs or wood";
        else return "Get " + itemGoal.getAmount() + " " + makeEnumLookBetter(itemGoal.getType().name());
    }

    private boolean playerInventorySufficient(Player player, int offset) {
        ItemStack[] heldItems =
                player.getInventory().getContents();
        int itemCount = offset;
        for (ItemStack stack : heldItems) {
            if(stack != null && isValidItem(stack)) itemCount += stack.getAmount();
        }
        return itemCount >= itemGoal.getAmount();
    }

    private boolean isValidItem(ItemStack goal){
        return ((generalType == null && itemGoal.getType().equals(goal.getType())) || (generalType != null && goal.getType().name().contains(generalType)));
    }
}
