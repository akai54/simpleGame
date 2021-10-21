package simpleGame.util;

/**
 * Provide operations to color strings for terminals that supports ANSI escapes codes.
 * ANSI escape codes taken from: https://stackoverflow.com/a/5762502
 */
public class StringColoring {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Supported colors.
     */
    public enum Color {
        BLACK, RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE
    }

    /**
     * Add a color to a string to be printed in a color-supporting terminal, using
     * standard ANSI color codes.
     * 
     * @param message The message to print.
     * @param color   The chosen color for the message.
     * @return A string prepended with the ANSI code corresponding to the chosen
     *         color, and appended with the ANSI color reset code.
     */
    public static String colorString(String message, Color color) {
        String outString = "";
        switch (color) {
            case CYAN:
                outString = ANSI_CYAN;
                break;
            case GREEN:
                outString = ANSI_GREEN;
                break;
            case PURPLE:
                outString = ANSI_PURPLE;
                break;
            case BLUE:
                outString = ANSI_BLUE;
                break;
            case RED:
                outString = ANSI_RED;
                break;
            case WHITE:
                outString = ANSI_WHITE;
                break;
            case BLACK:
                outString = ANSI_BLUE;
                break;
            case YELLOW:
                outString = ANSI_YELLOW;
                break;
            default:
                outString = ANSI_BLACK;
                break;
        }
        outString += message + ANSI_RESET;
        return outString;
    }

    /**
     * Overload for char parameters.
     */
    public static String colorString(char message, Color color) {
        return colorString("" + message, color);
    }

}