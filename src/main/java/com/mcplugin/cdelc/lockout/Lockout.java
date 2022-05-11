package com.mcplugin.cdelc.lockout;

import com.mcplugin.cdelc.lockout.commands.Lockoutadd;
import com.mcplugin.cdelc.lockout.commands.Lockoutaddall;
import com.mcplugin.cdelc.lockout.commands.Lockoutremove;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lockout extends JavaPlugin {

    GameInstance lockoutgame;

    @Override
    public void onEnable() {

        lockoutgame = null;
        this.getCommand("lockoutaddall").setExecutor(new Lockoutaddall(this));
        this.getCommand("lockoutadd").setExecutor(new Lockoutadd(this));
        this.getCommand("lockoutremove").setExecutor(new Lockoutremove(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public GameInstance getGame(){
        return lockoutgame;
    }
}
