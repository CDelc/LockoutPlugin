package com.mcplugin.cdelc.lockout.commands;

import com.mcplugin.cdelc.lockout.Lockout;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DebugTest extends LockoutCommand {


    public DebugTest(Lockout plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        return true;
    }

}
