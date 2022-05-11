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

    public StatisticTask(GameInstance instance, Statistic statisticType, int targetValue) {
        super(instance);
        this.statisticType = statisticType;
        this.targetValue = targetValue;
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
}
