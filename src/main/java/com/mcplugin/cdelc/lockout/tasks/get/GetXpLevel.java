package com.mcplugin.cdelc.lockout.tasks.get;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class GetXpLevel extends Task {

    int level;

    public GetXpLevel(GameInstance instance, int targetlevel, int diff) {
        super(instance, diff);
        level = targetlevel;
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof PlayerLevelChangeEvent){
            PlayerLevelChangeEvent event = (PlayerLevelChangeEvent) e;
            if(event.getNewLevel() >= level) complete(event.getPlayer());
        }
    }

    @Override
    public String getKeyword() {
        return "getLevel" + level;
    }

    @Override
    public String getDescription() {
        return "Get an XP level of at least " + level;
    }
}
