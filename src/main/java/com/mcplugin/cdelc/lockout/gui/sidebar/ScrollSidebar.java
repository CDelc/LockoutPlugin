package com.mcplugin.cdelc.lockout.gui.sidebar;

import com.mcplugin.cdelc.lockout.gui.GUIListener;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.function.Consumer;

public class ScrollSidebar implements Consumer<Event> {

    final int maxLine;

    String[] lines;
    LineData[] visibleLines;
    Objective sidebar;
    Scoreboard scoreboard;
    int currentLine;

    public ScrollSidebar(Scoreboard scoreboard, String title, int lineCount) {
        this.scoreboard = scoreboard;
        sidebar = scoreboard.registerNewObjective(super.toString(), "dummy", title);
        sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
        currentLine = 0;
        maxLine = Math.max(0, lineCount - 15);

        lines = new String[lineCount];
        for (int i=0; i<lineCount; i++) {
            lines[i] = "";
        }
        visibleLines = new LineData[15];
        for (int i=0; i<15; i++) {
            Team team = scoreboard.registerNewTeam(String.valueOf(i));
            String entry = generateLineEntry(i);
            team.addEntry(entry);
            visibleLines[i] = new LineData(team, entry);
            sidebar.getScore(entry).setScore(15 - i);
        }

        GUIListener.singleton().register(this);
    }

    public void setLine(int line, String content) {
        assert line >= 0 && line < lines.length;
        if (content.length() > 64) content = content.substring(0, 61) + "...";
        else content = String.format("%-64s", content);
        lines[line] = content;

        int visibleIndex = line - currentLine;
        if (visibleIndex >= 0 && visibleIndex < 15) {
            visibleLines[visibleIndex].getTeam().setPrefix(lines[line]);
        }
    }

    public void scroll(int numLines) {
        int newLine = Math.max(Math.min(currentLine + numLines, maxLine), 0);
        if (newLine != currentLine) {
            currentLine = newLine;
            updateAll();
        }
    }

    private void updateAll() {
        for (int i=currentLine; i<currentLine+15 && i<lines.length; i++) {
            int visibleIndex = i - currentLine;
            sidebar.getScore(visibleLines[visibleIndex].getEntry()).setScore(lines.length - i);
            visibleLines[visibleIndex].getTeam().setPrefix(lines[i]);
        }
    }

    private String generateLineEntry(int line) {
        assert line >= 0;
        StringBuilder lineEntry = new StringBuilder();
         do {
            lineEntry.append(LineEntry.getByNumber(line % 15));
            line /= 15;
        } while (line > 0);
        return lineEntry.toString();
    }

    /**
     * Handles PlayerItemHeldEvents given by the GUIListener
     * Discards all events but PlayerItemHeldEvent
     * @param event
     */
    @Override
    public void accept(Event event) {
        if (!(event instanceof PlayerItemHeldEvent)) return;
        PlayerItemHeldEvent playerItemHeldEvent = (PlayerItemHeldEvent) event;
        int diff = playerItemHeldEvent.getNewSlot() - playerItemHeldEvent.getPreviousSlot();
        if (diff == 1 || diff == -8) scroll(3);
        else if (diff == -1 || diff == 8) scroll(-3);
    }

    /**
     * Unregisters all associated Teams and Objectives from the Scoreboard.
     * Stops consuming events from the ScrollListener
     */
    public void unregister() {
        for (LineData lineData : visibleLines) {
            lineData.getTeam().unregister();
        }
        sidebar.unregister();
        GUIListener.singleton().unregister(this);
    }

    private static class LineData {
        Team team;
        String entry;

        public LineData(Team team, String entry) {
            this.team = team;
            this.entry = entry;
        }

        public Team getTeam() {
            return team;
        }

        public String getEntry() {
            return entry;
        }
    }
}
