package com.mcplugin.cdelc.lockout.gui;

import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.ChatColor;

public class TaskEntry {

    static final String TEXT_EFFECT_INCOMPLETE = "";
    static final String TEXT_EFFECT_SUCCESS = ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH;
    static final String TEXT_EFFECT_FAILURE = ChatColor.RED + "" + ChatColor.STRIKETHROUGH;


    String entryText;
    EntryState state; // Made of ChatColor enum toString output

    public TaskEntry(Task task) {
        entryText = taskRepr(task);
        state = EntryState.INCOMPLETE;
    }

    public void complete(boolean thisPlayer) {
        if (thisPlayer) {
            setState(EntryState.SUCCESS);
        } else {
            setState(EntryState.FAILURE);
        }
    }

    public EntryState getState() {
        return state;
    }

    public void setState(EntryState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state.textEffect() + entryText + ChatColor.RESET;
    }

    /**
     * Checks whether the given task is represented by this entry's text
     * @param task
     * @return
     */
    public boolean isForTask(Task task) {
        return this.entryText.equals(taskRepr(task));
    }

    private String taskRepr(Task task) {
        return task.getDescription();
    }

    public enum EntryState {
        INCOMPLETE(TEXT_EFFECT_INCOMPLETE),
        SUCCESS(TEXT_EFFECT_SUCCESS),
        FAILURE(TEXT_EFFECT_FAILURE);

        final String TEXT_EFFECT;

        EntryState(String effect) { TEXT_EFFECT = effect; }

        public String textEffect() {
            return TEXT_EFFECT;
        }
    }
}
