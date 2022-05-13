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

public class GetTask extends Task {

    ItemStack itemGoal;

    public GetTask(GameInstance game, ItemStack itemGoal, int diff) {
        super(game, diff);
        this.itemGoal = itemGoal;
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
                if(itemGoal.isSimilar(pickupEvent.getItem().getItemStack()) &&
                        playerInventorySufficient(player, pickupEvent.getItem().getItemStack().getAmount())) {
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

    private boolean playerInventorySufficient(Player player, int offset) {
        Collection<? extends ItemStack> heldItems =
                player.getInventory().all(itemGoal.getData().getItemType()).values();
        int itemCount = offset;
        for (ItemStack stack : heldItems) {
            itemCount += stack.getAmount();
        }
        return itemCount >= itemGoal.getAmount();
    }
}
