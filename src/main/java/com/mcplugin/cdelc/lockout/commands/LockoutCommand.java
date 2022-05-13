package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class LockoutCommand implements CommandExecutor {

    GameInstance game;

    public LockoutCommand(Lockout plugin){
        game = plugin.getGame();
    }

    public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);
}
