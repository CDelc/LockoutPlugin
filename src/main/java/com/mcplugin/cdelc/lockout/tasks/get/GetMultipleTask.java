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

    ItemStack[] itemGoals;
    int count;
    String shortd;
    String longd;

    public GetMultipleTask(GameInstance game, ItemStack[] itemGoals, int numRequired, int diff, String shortDesc, String longDesc) {
        super(game, diff);
        this.itemGoals = itemGoals;
        count = numRequired;
        shortd = shortDesc;
        longd = longDesc;
    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof InventoryCloseEvent) {
            InventoryCloseEvent closeEvent = (InventoryCloseEvent) e;
            Player player = (Player) closeEvent.getPlayer();
            if (playerInventorySufficient(player, null) >= count) complete(player);
        }
        else if (e instanceof EntityPickupItemEvent) {
            EntityPickupItemEvent pickupEvent = (EntityPickupItemEvent) e;
            if (pickupEvent.getEntityType() == EntityType.PLAYER) {
                Player player = (Player) pickupEvent.getEntity();
                ItemStack offset = pickupEvent.getItem().getItemStack();
                if(playerInventorySufficient(player, offset) >= count) complete(player);
            }
        }
    }

    @Override
    public String getKeyword() {
        if(shortd != null) return shortd;
        StringBuilder keyword = new StringBuilder("get");
        if(count < itemGoals.length) keyword.append(" at least " + count + " of the following: ");
        for (ItemStack item : itemGoals) {
            keyword.append(item.getType());
        }
        return keyword.toString();
    }

    @Override
    public String getDescription() {
        if(longd != null) return longd;
        StringBuilder description = new StringBuilder("Get ");
        for (int i = 0; i < itemGoals.length; i++) {
            ItemStack item = itemGoals[i];
            description.append(String.format("%d %s", item.getAmount(), makeEnumLookBetter(item.getType().name())));
            if(itemGoals.length - i > 1) description.append(" and ");
        }
        return description.toString();
    }

    private int playerInventorySufficient(Player player, ItemStack offset) {
        ItemStack[] heldItems =
                player.getInventory().getContents();
        int reqSatisfied = 0;
        for (ItemStack item : itemGoals) {
            int itemCount = 0;
            for (ItemStack stack : heldItems) {
                if(stack != null && stack.getType().equals(item.getType())) itemCount += stack.getAmount();
            }
            if(offset != null && offset.getType().equals(item.getType())) itemCount += offset.getAmount();
            if (itemCount >= item.getAmount()) reqSatisfied++;
        }
        return reqSatisfied;
    }
}
