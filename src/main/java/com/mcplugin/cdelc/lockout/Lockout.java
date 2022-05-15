package com.mcplugin.cdelc.lockout;

import com.mcplugin.cdelc.lockout.commands.*;
import com.mcplugin.cdelc.lockout.gui.sidebar.ScrollListener;
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
        this.getCommand("lockoutabsolutedifficulty").setExecutor(new Lockoutabsolutedifficulty(this));
        this.getCommand("lockoutmindifficulty").setExecutor(new Lockoutmindifficulty(this));
        this.getCommand("lockoutmaxdifficulty").setExecutor(new Lockoutmaxdifficulty(this));
        this.getCommand("lockoutnumtasks").setExecutor(new Lockoutnumtasks(this));
        this.getCommand("lockoutreset").setExecutor(new Lockoutreset(this));
        this.getCommand("lockoutsettings").setExecutor(new Lockoutsettings(this));
        this.getCommand("lockoutstart").setExecutor(new Lockoutstart(this));
        this.getCommand("lockoutstop").setExecutor(new Lockoutstop(this));
        this.getCommand("listtasks").setExecutor(new ListTasks(this));
        getServer().getPluginManager().registerEvents(new TaskListener(this), this);
        getServer().getPluginManager().registerEvents(new ScrollListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public GameInstance getGame(){
        return lockoutgame;
    }

    public void resetGame(){
        this.onEnable();
    }
}
