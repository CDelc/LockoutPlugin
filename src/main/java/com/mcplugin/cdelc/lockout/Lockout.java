package com.mcplugin.cdelc.lockout;

import com.mcplugin.cdelc.lockout.commands.*;
import com.mcplugin.cdelc.lockout.tasks.TaskListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lockout extends JavaPlugin {

    GameInstance lockoutgame;

    @Override
    public void onEnable() {

        lockoutgame = new GameInstance();
        this.getCommand("lockoutaddall").setExecutor(new Lockoutaddall(this));
        this.getCommand("lockoutadd").setExecutor(new Lockoutadd(this));
        this.getCommand("lockoutremove").setExecutor(new Lockoutremove(this));
        this.getCommand("debugtest").setExecutor(new DebugTest(this));
        getServer().getPluginManager().registerEvents(new TaskListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public GameInstance getGame(){
        return lockoutgame;
    }
}
