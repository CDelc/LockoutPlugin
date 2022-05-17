package com.mcplugin.cdelc.lockout.gui;

import com.mcplugin.cdelc.lockout.gui.sidebar.ScrollSidebar;
import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class TaskSidebar {

    List<TaskEntry> taskEntries;

    LockoutScoreboard scoreboard;
    ScrollSidebar sidebar;

    public TaskSidebar(LockoutScoreboard scoreboard) {
        this.scoreboard = scoreboard;
        taskEntries = new ArrayList<>();
        this.sidebar = new ScrollSidebar(
                scoreboard.getScoreboard(),
                ChatColor.DARK_RED + "" + ChatColor.BOLD + "LOCKOUT"
        );
    }

    /**
     * Unregisters from GUIListener
     */
    public void unregister() {
        sidebar.unregister();
    }

    /**
     * Updates given task's appearance on the sidebar, according to who completed the task
     * @param task
     * @param whoCompleted
     */
    public void onTaskComplete(Task task, Player whoCompleted) {
        Optional<TaskEntry> optEntry = getEntryFromTask(task);
        if (!optEntry.isPresent()) return;

        TaskEntry entry = optEntry.get();
        entry.complete(whoCompleted.equals(scoreboard.getOwner()));
        int line = taskEntries.indexOf(entry);
        sidebar.setLine(line, entry.toString());
    }

    /**
     * Initialize the sidebar with a collection of tasks.
     * This will override any objective in the sidebar slot with a sidebar of tasks.
     * @param tasks
     */
    public void registerTasks(Collection<Task> tasks) {
        Iterator<Task> taskIter = tasks.iterator();
        for (int i=0; i<tasks.size(); i++) {
            TaskEntry entry = new TaskEntry(taskIter.next());
            taskEntries.add(entry);
            sidebar.setLines(taskEntries.stream().map(TaskEntry::toString).collect(Collectors.toList()));
        }
    }

    /**
     * Removes all tasks from the sidebar
     */
    public void clear() {
        taskEntries.clear();
        sidebar.clear();
    }

    /**
     * Gives the TaskEntry associated with the given task, if it exists
     * @param t
     * @return
     */
    private Optional<TaskEntry> getEntryFromTask(Task t) {
        return taskEntries.stream().filter(te -> te.isForTask(t)).findFirst();
    }

    public void show() {
        sidebar.show();
    }

    public void hide() {
        sidebar.hide();
    }
}
