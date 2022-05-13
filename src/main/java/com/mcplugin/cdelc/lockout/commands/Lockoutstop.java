package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lockoutstop extends LockoutCommand{
    public Lockoutstop(Lockout plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!game.running()){
            sender.sendMessage(ChatColor.RED + "No game running to stop");
            return true;
        }
        game.stop();
        for(Player p : Bukkit.getServer().getOnlinePlayers()) p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Lockdown game has been stopped");
        return true;
    }
}
