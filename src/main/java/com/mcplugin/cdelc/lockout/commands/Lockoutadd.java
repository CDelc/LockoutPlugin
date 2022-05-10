package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.Bukkit;
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

        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            sender.sendMessage(Color.RED + "Player not found.");
            return false;
        }
        instance.getGame().addPlayer(target);
        return true;
    }
}
