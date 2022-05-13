package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Lockoutsettings extends LockoutCommand{

    public Lockoutsettings(Lockout plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(game.settingsToString());

        return true;
    }
}
