package com.mcplugin.cdelc.lockout.gui.sidebar;

import org.bukkit.ChatColor;

public enum LineEntry {
    LINE0(ChatColor.BLACK + ""),
    LINE1(ChatColor.DARK_BLUE + ""),
    LINE2(ChatColor.DARK_GREEN + ""),
    LINE3(ChatColor.DARK_AQUA + ""),
    LINE4(ChatColor.DARK_RED + ""),
    LINE5(ChatColor.DARK_PURPLE + ""),
    LINE6(ChatColor.GOLD + ""),
    LINE7(ChatColor.GRAY + ""),
    LINE8(ChatColor.DARK_GRAY + ""),
    LINE9(ChatColor.BLUE + ""),
    LINE10(ChatColor.GREEN + ""),
    LINE11(ChatColor.AQUA + ""),
    LINE12(ChatColor.RED + ""),
    LINE13(ChatColor.LIGHT_PURPLE + ""),
    LINE14(ChatColor.YELLOW + "");

    private final String toString;

    LineEntry(String toString) {
        this.toString = toString;
    }

    public String toString() {
        return toString;
    }

    public static LineEntry getByNumber(int lineNumber) {
        switch (lineNumber) {
            case 0:
                return LineEntry.LINE0;
            case 1:
                return LineEntry.LINE1;
            case 2:
                return LineEntry.LINE2;
            case 3:
                return LineEntry.LINE3;
            case 4:
                return LineEntry.LINE4;
            case 5:
                return LineEntry.LINE5;
            case 6:
                return LineEntry.LINE6;
            case 7:
                return LineEntry.LINE7;
            case 8:
                return LineEntry.LINE8;
            case 9:
                return LineEntry.LINE9;
            case 10:
                return LineEntry.LINE10;
            case 11:
                return LineEntry.LINE11;
            case 12:
                return LineEntry.LINE12;
            case 13:
                return LineEntry.LINE13;
            case 14:
                return LineEntry.LINE14;
            default:
                return null;
        }
    }
}
