package com.mcplugin.cdelc.lockout.gui.sidebar;

import com.mcplugin.cdelc.lockout.gui.GUIListener;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ScrollSidebar implements Consumer<Event> {

    List<String> lines;
    LineData[] visibleLines;
    Objective sidebar;
    Scoreboard scoreboard;

    int currentLine;
    int maxLine;

    public ScrollSidebar(Scoreboard scoreboard, String title) {
        this.scoreboard = scoreboard;
        sidebar = scoreboard.registerNewObjective(super.toString(), "dummy", title);
        currentLine = 0;
        visibleLines = new LineData[15];
        for (int i=0; i<15; i++) {
            Team team = scoreboard.registerNewTeam(String.valueOf(i));
            String entry = generateLineEntry(i);
            team.addEntry(entry);
            visibleLines[i] = new LineData(team, entry);
            sidebar.getScore(entry).setScore(15 - i);
        }

        setLines(new ArrayList<>());
        resize(0);

        GUIListener.singleton().register(this);
    }

    /**
     * Sets the content of a specific line.
     * Scoreboard will resize as needed if the provided line number exceeds the size
     * @param line
     * @param content
     */
    public void setLine(int line, String content) {
        assert line >= 0;
        if (line >= lines.size()) resize(line + 1);

        content = generateLine(content);
        lines.set(line, content);

        int visibleIndex = line - currentLine;
        if (visibleIndex >= 0 && visibleIndex < 15) {
            visibleLines[visibleIndex].getTeam().setPrefix(lines.get(line));
        }
    }

    /**
     * Replaces all lines with the given list of lines
     * @param lines
     */
    public void setLines(List<String> lines) {
        this.lines = lines.stream().map(s -> generateLine(s)).collect(Collectors.toList());
        this.resize(lines.size());
        updateAll();
    }

    /**
     * Scrolls up or down by the number of lines (+ for up, - for down)
     * @param numLines
     */
    public void scroll(int numLines) {
        int newLine = Math.max(Math.min(currentLine + numLines, maxLine), 0);
        if (newLine != currentLine) {
            currentLine = newLine;
            updateAll();
        }
    }

    /**
     * Sets the current line
     * Clamps to the range of valid lines. Will not resize lines
     * @param line
     */
    public void setCurrentLine(int line) {
        line = Math.max(Math.min(line, maxLine), 0);
        if (line != currentLine) {
            currentLine = line;
            updateAll();
        }
    }

    /**
     * Updates all sidebar lines with the content in lines
     */
    private void updateAll() {
        for (int i=currentLine; i<currentLine+15; i++) {
            int visibleIndex = i - currentLine;
            sidebar.getScore(visibleLines[visibleIndex].getEntry()).setScore(lines.size() - i);
            visibleLines[visibleIndex].getTeam().setPrefix(getLine(i));
        }
    }

    /**
     * Generates an invisible entry, unique to the given line number
     * @param line
     * @return
     */
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

    /**
     * Removes lines of whitespace at the bottom of the sidebar
     */
    public void trimEmptyLines() {
        int endOfContent = lines.size() - 1;
        while (endOfContent > 0 && lines.get(endOfContent).isBlank()) endOfContent--;
        resize(endOfContent + 1);
    }

    /**
     * Gets the content of the line
     * If no content is defined, returns a line of whitespace
     * @param line
     * @return
     */
    public String getLine(int line) {
        try {
            return lines.get(line);
        }
        catch (IndexOutOfBoundsException e) {
            return generateLine("");
        }
    }

    /**
     * Shows this sidebar on the scoreboard
     */
    public void show() {
        sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    /**
     * Hides this sidebar from the scoreboard
     */
    public void hide() {
        sidebar.setDisplaySlot(null);
    }

    /**
     * Generates a length 64 String, containing given content.
     * Content longer than 64 characters will be cut short.
     * @param content
     * @return
     */
    private String generateLine(String content) {
        if (content.length() < 64) return String.format("%s", content);
        return content.substring(0, 61) + "...";
    }

    /**
     * Resizes the number of lines in the sidebar to newSize
     * @param newSize
     */
    private void resize(int newSize) {
        assert newSize >= 0;
        if (newSize < lines.size()) {
            while (lines.size() > newSize) lines.remove(lines.size() - 1);
        }
        else if (newSize > lines.size()){
            while (lines.size() < newSize) lines.add(generateLine(""));
        }
        maxLine = Math.max(0, lines.size() - 15);
        setCurrentLine(currentLine);
    }

    public void clear() {
        resize(0);
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
