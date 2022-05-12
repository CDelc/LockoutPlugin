package com.mcplugin.cdelc.lockout.tasks.get;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class GetMultipleTask extends Task {

    Collection<ItemStack> itemGoals;

    public GetMultipleTask(GameInstance game, Collection<ItemStack> itemGoals, int diff) {
        super(game, diff);
        this.itemGoals = itemGoals;
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
                if(playerInventorySufficient(player)) complete(player);
            }
        }
    }

    @Override
    public String getKeyword() {
        StringBuilder keyword = new StringBuilder("get");
        for (ItemStack item : itemGoals) {
            keyword.append(item.getType());
        }
        return keyword.toString();
    }

    @Override
    public String getDescription() {
        StringBuilder description = new StringBuilder("Get ");
        for (ItemStack item : itemGoals) {
            description.append(String.format("%d %s\n", item.getAmount(), item.getType()));
        }
        return description.toString();
    }

    private boolean playerInventorySufficient(Player player) {
        for (ItemStack item : itemGoals) {
            Collection<? extends ItemStack> heldItems = player.getInventory().all(item.getType()).values();
            int itemCount = 0;
            for (ItemStack stack : heldItems) {
                itemCount += stack.getAmount();
            }
            if (itemCount < item.getAmount()) return false;
        }
        return true;
    }
}
