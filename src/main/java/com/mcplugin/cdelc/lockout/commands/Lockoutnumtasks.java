package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Lockoutnumtasks extends LockoutCommand{
    public Lockoutnumtasks(Lockout plugin) {
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
        Integer numTasks = -1;
        try{
            numTasks = Integer.parseInt(args[0]);
        }
        catch(NumberFormatException e){
            sender.sendMessage(ChatColor.RED + "Must give a number");
            return true;
        }
        if(numTasks < 0 || numTasks > game.getMaxTasks()){
            sender.sendMessage(ChatColor.RED + "Task count must be between 0 and " + game.getMaxTasks());
            return true;
        }
        game.setNumTasks(numTasks);
        return true;
    }
}
