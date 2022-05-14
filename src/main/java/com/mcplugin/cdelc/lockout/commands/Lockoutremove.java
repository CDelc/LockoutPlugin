package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.locks.Lock;

public class Lockoutremove extends LockoutCommand {

    public Lockoutremove(Lockout plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0) return false;

        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            sender.sendMessage(Color.RED + "Player not found.");
            return true;
        }
        if(!(game.getPlayers().contains(target))){
            sender.sendMessage(ChatColor.RED + target.getName() + " not in game.");
            return true;
        }
        boolean rc = game.removePlayer(target);
        if(!rc) sender.sendMessage(Color.RED + "Player not in lockout game.");

        sender.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.DARK_RED + " removed from the game");
        target.sendMessage(ChatColor.DARK_RED + "You have been removed from the lockout game");
        return true;
    }
}
