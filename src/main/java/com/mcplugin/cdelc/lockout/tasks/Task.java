package com.mcplugin.cdelc.lockout.tasks;

import com.mcplugin.cdelc.lockout.GameInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import java.util.Locale;

public abstract class Task implements Listener {

    GameInstance parentGame;
    boolean isComplete;
    protected int difficulty;

    public Task(GameInstance instance, int diff){
        parentGame = instance;
        isComplete = false;
        difficulty = diff;
    }

    public abstract void onEvent(Event e);
    public abstract String getKeyword();
    public abstract String getDescription();
    public int getDifficulty(){
        return difficulty;
    }

    protected void complete(Player p){
        if(isComplete || !(parentGame.getPlayers().contains(p))) return;
        isComplete = true;
        parentGame.completeTask(this, p);
    }

    public void uncomplete(){
        isComplete = false;
    }

    protected static String makeEnumLookBetter(String enumString){
        String output = "";
        for(Character c : enumString.toCharArray()){
            if(c.equals('_')) output += " ";
            else output += c.toString().toLowerCase(Locale.ROOT);
        }
        return output;
    }


}
