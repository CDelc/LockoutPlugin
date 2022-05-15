package com.mcplugin.cdelc.lockout.gui;

import com.mcplugin.cdelc.lockout.tasks.Task;
import org.bukkit.ChatColor;

public class TaskEntry {

    static final String DEFAULT_TEXT_EFFECT_INCOMPLETE = "";
    static final String DEFAULT_TEXT_EFFECT_COMPLETE_SUCCESS = ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH;
    static final String DEFAULT_TEXT_EFFECT_COMPLETE_FAILURE = ChatColor.RED + "" + ChatColor.STRIKETHROUGH;

    final String TEXT_EFFECT_INCOMPLETE;
    final String TEXT_EFFECT_COMPLETE_SUCCESS;
    final String TEXT_EFFECT_COMPLETE_FAILURE;

    String entryText;
    String entryTextEffects; // Made of ChatColor enum toString output

    public TaskEntry(Task task, String txtEffIncomplete, String txtEffCompleteSuccess, String txtEffCompleteFail) {
        TEXT_EFFECT_INCOMPLETE = txtEffIncomplete;
        TEXT_EFFECT_COMPLETE_SUCCESS = txtEffCompleteSuccess;
        TEXT_EFFECT_COMPLETE_FAILURE = txtEffCompleteFail;

        entryText = task.getDescription();
        entryTextEffects = TEXT_EFFECT_INCOMPLETE;
    }

    public TaskEntry(Task task, String txtEffIncomplete) {
        this(
                task,
                txtEffIncomplete,
                DEFAULT_TEXT_EFFECT_COMPLETE_SUCCESS,
                DEFAULT_TEXT_EFFECT_COMPLETE_FAILURE
        );
    }

    public TaskEntry(Task task) {
        this(
                task,
                DEFAULT_TEXT_EFFECT_INCOMPLETE,
                DEFAULT_TEXT_EFFECT_COMPLETE_SUCCESS,
                DEFAULT_TEXT_EFFECT_COMPLETE_FAILURE
        );
    }

    public void complete(boolean thisPlayer) {
        if (thisPlayer) {
            entryTextEffects = ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH;
        } else {
            entryTextEffects = ChatColor.RED + "" + ChatColor.STRIKETHROUGH;
        }
    }

    @Override
    public String toString() {
        return entryTextEffects + entryText + ChatColor.RESET;
    }

    public boolean isForTask(Task task) {
        return this.entryText.equals(task.getDescription());
    }
}
