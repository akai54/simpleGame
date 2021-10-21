package simpleGame.util;

public class CharUtil {

    /**
     * Transform a digit in a letter (1 → A, 2 → B, etc.)
     * @param i The digit.
     * @return The letter corresponding to the digit.
     */
    public static char getCharForNumber(int i) {
        return i > 0 && i < 27 ? ((char) (i + 'A' - 1)) : null;
    }
}