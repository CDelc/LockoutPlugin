package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class LockoutCommand implements CommandExecutor {

    Lockout instance;

    public LockoutCommand(Lockout plugin){
        instance = plugin;
    }

    public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);
}
