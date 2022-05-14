package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.Lockout;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ListTasks extends LockoutCommand{
    public ListTasks(Lockout plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for(Task t : game.getSelectedTasks()) sender.sendMessage(ChatColor.AQUA + t.getDescription());
        return true;
    }
}
