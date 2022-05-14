package com.mcplugin.cdelc.lockout.events;

import java.util.function.Consumer;

public interface LockoutEventListener extends Consumer<LockoutEvent> {
    @Override
    default void accept(LockoutEvent e) {
        if (e instanceof TaskCompleteEvent) {
            onTaskCompleteEvent((TaskCompleteEvent) e);
        }
        // extend with more event types
    }

    default void onTaskCompleteEvent(TaskCompleteEvent e) { }
}
