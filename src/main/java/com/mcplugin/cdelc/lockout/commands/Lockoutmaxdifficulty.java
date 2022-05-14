package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.concurrent.locks.Lock;

public class Lockoutmaxdifficulty extends LockoutCommand {
    public Lockoutmaxdifficulty(Lockout plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 1){
            return false;
        }
        if(game.running()){
            sender.sendMessage(ChatColor.RED + "Cannot change settings while game is in progress");
            return true;
        }
        Integer diff = -1;
        try{
            diff = Integer.parseInt(args[0]);
        }
        catch(NumberFormatException e){
            sender.sendMessage(ChatColor.RED + "Must give a number 0-4");
            return true;
        }
        if(diff < 0 || diff > 4){
            sender.sendMessage(ChatColor.RED + "Difficulty out of range, must be 0-4");
            return true;
        }
        if(diff > game.getMaxDiff()){
            sender.sendMessage(ChatColor.RED + "Maximum must be more than minimum");
            return true;
        }
        game.setMaxDiff(diff);

        return true;
    }
}
