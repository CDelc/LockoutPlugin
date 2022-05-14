package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lockoutadd extends Lockoutaddall {

    public Lockoutadd(Lockout plugin){
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length < 1) return false;
        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            sender.sendMessage(Color.RED + "Player not found.");
            return true;
        }
        if(game.getPlayers().contains(target)){
            sender.sendMessage(ChatColor.RED + target.getName() + " already in game.");
            return true;
        }
        game.addPlayer(target);
        sender.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.YELLOW + " added to the game");
        target.sendMessage(ChatColor.YELLOW + "You have been added to the lockout game");
        return true;
    }
}
