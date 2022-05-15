package com.mcplugin.cdelc.lockout.gui;

import com.mcplugin.cdelc.lockout.gui.sidebar.ScrollSidebar;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class TaskSidebar {

    HashMap<TaskEntry, Integer> taskEntryLines;

    LockoutScoreboard scoreboard;
    ScrollSidebar sidebar;

    public TaskSidebar(LockoutScoreboard scoreboard) {
        this.scoreboard = scoreboard;
        taskEntryLines = new HashMap<>();
    }

    public void unregister() {
        sidebar.unregister();
    }

    public void onTaskComplete(Task task, Player player) {
        Optional<TaskEntry> optEntry = taskEntryLines.keySet().stream().filter(te -> te.isForTask(task)).findFirst();
        if (!optEntry.isPresent()) return;

        TaskEntry entry = optEntry.get();
        entry.complete(player.equals(scoreboard.getOwner()));
        int line = taskEntryLines.get(entry);
        sidebar.setLine(line, entry.toString());
    }

    public void registerTasks(Collection<Task> tasks) {
        sidebar = new ScrollSidebar(
                scoreboard.getScoreboard(),
                ChatColor.DARK_RED + "" + ChatColor.BOLD + "LOCKOUT",
                tasks.size()
        );

        Iterator<Task> taskIter = tasks.iterator();
        for (int i=0; i<tasks.size(); i++) {
            TaskEntry entry = new TaskEntry(taskIter.next());
            taskEntryLines.put(entry, i);
            sidebar.setLine(i, entry.toString());
        }
    }
}
