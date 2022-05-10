package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lockoutaddall extends LockoutCommand {

    public Lockoutaddall(Lockout plugin){
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        for(Player p : Bukkit.getServer().getOnlinePlayers()) instance.getGame().addPlayer(p);
        return true;
    }

}
