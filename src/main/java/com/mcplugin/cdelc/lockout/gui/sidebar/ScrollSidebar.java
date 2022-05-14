package com.mcplugin.cdelc.lockout.gui.sidebar;

import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.function.Consumer;

public class ScrollSidebar implements Consumer<PlayerItemHeldEvent> {

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
            Team team = scoreboard.registerNewTeam(generateLineTeamName(i));
            String entry = generateLineEntry(i);
            team.addEntry(entry);
            visibleLines[i] = new LineData(team, entry);
        }

        ScrollListener.singleton().register(this);
    }

    public void setLine(int line, String content) {
        assert line >= 0 && line < lines.length;
        if (content.length() > 64) {
            content = content.substring(0, 61) + "...";
        }
        lines[line] = content;

        int visibleIndex = line - currentLine;
        if (visibleIndex >= 0 && visibleIndex < 15) {
            visibleLines[visibleIndex].getTeam().setPrefix(lines[line]);
        }
    }

    public void pageDown() {
        int newLine = Math.min(currentLine + 15, maxLine);
        if (newLine != currentLine) {
            currentLine = newLine;
            updateAll();
        }
    }

    public void pageUp() {
        int newLine = Math.max(currentLine - 15, 0);
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

    private String generateLineTeamName(int line) {
        return super.toString() + "line" + line;
    }

    /**
     * Handles PlayerItemHeldEvents given by the ScrollListener
     * @param playerItemHeldEvent
     */
    @Override
    public void accept(PlayerItemHeldEvent playerItemHeldEvent) {
        int diff = playerItemHeldEvent.getNewSlot() - playerItemHeldEvent.getPreviousSlot();
        if (diff == 1 || diff == -8) pageUp();
        else if (diff == -1 || diff == 8) pageDown();
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
        ScrollListener.singleton().unregister(this);
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
