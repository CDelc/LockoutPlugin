package com.mcplugin.cdelc.lockout.tasks.get;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class GetTask extends Task {

    Material item;
    int quantity;

    public GetTask(GameInstance instance, Material item, int quantity) {
        super(instance);
        this.item = item;
        this.quantity = quantity;
    }

    public GetTask(GameInstance instance, ItemStack itemGoal) {
        this(instance, itemGoal.getType(), itemGoal.getAmount());
    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof InventoryCloseEvent) {
            InventoryCloseEvent closeEvent = (InventoryCloseEvent) e;
            Player player = (Player) closeEvent.getPlayer();
            if (playerInventorySufficient(player)) complete(player);
        }
        else if (e instanceof EntityPickupItemEvent) {
            EntityPickupItemEvent pickupEvent = (EntityPickupItemEvent) e;
            if (pickupEvent.getEntityType() == EntityType.PLAYER) {
                Player player = (Player) pickupEvent.getEntity();
                if(item == pickupEvent.getItem().getItemStack().getType() &&
                        playerInventorySufficient(player)) {
                    complete(player);
                }
            }
        }
    }

    @Override
    public String getKeyword() {
        return "get" + itemGoal.getAmount() + "" + itemGoal.getType();
    }

    @Override
    public String getDescription() {
        return "Get " + itemGoal.getAmount() + " " + itemGoal.getType();
    }

    private boolean playerInventorySufficient(Player player) {
        Collection<? extends ItemStack> heldItems =
                player.getInventory().all(item).values();
        int itemCount = 0;
        for (ItemStack stack : heldItems) {
            itemCount += stack.getAmount();
        }
        return itemCount >= quantity;
    }
}
