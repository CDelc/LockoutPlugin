package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lockoutstart extends LockoutCommand{
    public Lockoutstart(Lockout plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(game.running()){
            sender.sendMessage(ChatColor.RED + "Game already in progress");
            return true;
        }
        game.start();
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "LOCKOUT HAS BEGUN. GOOD LUCK.");
        }
        return true;
    }
}
