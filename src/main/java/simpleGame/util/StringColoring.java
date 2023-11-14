package simpleGame.util;

import static org.fusesource.jansi.Ansi.ansi;
/**
 * Provide operations to color strings for terminals that supports ANSI escapes codes.
 */
public class StringColoring {

    /**
     * Supported colors.
     */
    public enum GameColor {
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
    public static String colorString(String message, GameColor color) {
        return ansi().fg(org.fusesource.jansi.Ansi.Color.valueOf(color.toString())).a(message).reset().toString();
    }

    /**
     * Overload for char parameters.
     */
    public static String colorString(char message, GameColor color) {
        return colorString("" + message, color);
    }

}