package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lockoutreset extends LockoutCommand{

    Lockout pluginInstance;

    public Lockoutreset(Lockout plugin) {
        super(plugin);
        pluginInstance = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(game.running()) {
            game.stop();
            for(Player p : Bukkit.getServer().getOnlinePlayers()) p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Lockdown game has been stopped");
        }
        game.clear();
        pluginInstance.resetGame();
        sender.sendMessage(ChatColor.YELLOW + "Game reset");
        return true;
    }
}
