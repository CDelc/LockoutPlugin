package com.mcplugin.cdelc.lockout.tasks.statistic;

import com.mcplugin.cdelc.lockout.GameInstance;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;

public class StatisticTask extends Task {

    Statistic statisticType;
    int targetValue;

    public StatisticTask(GameInstance instance, Statistic statisticType, int targetValue, int diff) {
        super(instance);
        this.statisticType = statisticType;
        this.targetValue = targetValue;
        difficulty = diff;
    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof PlayerStatisticIncrementEvent) {
            PlayerStatisticIncrementEvent statEvent = (PlayerStatisticIncrementEvent) e;

            if (statEvent.getStatistic() == statisticType) {
                Player player = statEvent.getPlayer();
                if (player.getStatistic(statisticType) >= targetValue) {
                    complete(player);
                }
            }
        }
    }

    @Override
    public String getKeyword() {
        return statisticType.name() + ":" + targetValue;
    }

    @Override
    public String getDescription() {
        return "Get the value of the " + statisticType.name() + " statistic to " + targetValue;
    }
}
