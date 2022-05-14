package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Lockoutabsolutedifficulty extends LockoutCommand {

    GameInstance game;

    public Lockoutabsolutedifficulty(Lockout plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 1){
            return false;
        }
        if(game.running()){
            sender.sendMessage(ChatColor.RED + "Cannot change settings while game is in progress");
            return false;
        }
        Integer diff = -1;
        try{
            diff = Integer.parseInt(args[0]);
        }
        catch(NumberFormatException e){
            sender.sendMessage(ChatColor.RED + "Must give a number 0-4");
            return false;
        }
        if(diff < 0 || diff > 4){
            sender.sendMessage(ChatColor.RED + "Difficulty out of range, must be 0-4");
            return false;
        }
        game.setMaxDiff(diff);
        game.setMinDiff(diff);

        return true;
    }
}
