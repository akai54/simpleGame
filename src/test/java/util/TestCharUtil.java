package util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import simpleGame.util.CharUtil;

public class TestCharUtil {

    @Test
    public void testValidInputs() {
        // Itère à travers les nombres de 1 à 26 et teste-les contre leurs caractères ASCII correspondants
        for (int i = 1; i <= 26; i++) {
            char expectedChar = (char) ('A' + i - 1);
            assertEquals(expectedChar, CharUtil.getCharForNumber(i),
                    i + " devrait être converti en '" + expectedChar + "'");
        }
    }


    @Test
    public void testLowerBoundary() {
        assertNull(CharUtil.getCharForNumber(0), "0 devrait renvoyer null car il est hors de la plage valide");
    }

    @Test
    public void testJustBelowLowerBoundary() {
        assertNull(CharUtil.getCharForNumber(-1), "-1 devrait renvoyer null car il est hors de la plage valide");
    }

    @Test
    public void testUpperBoundary() {
        assertNull(CharUtil.getCharForNumber(27), "27 devrait renvoyer null car il est hors de la plage valide");
    }

    @Test
    public void testJustAboveUpperBoundary() {
        assertNull(CharUtil.getCharForNumber(28), "28 devrait renvoyer null car il est hors de la plage valide");
    }

    @Test
    public void testLargePositiveInput() {
        assertNull(CharUtil.getCharForNumber(Integer.MAX_VALUE), "Integer.MAX_VALUE devrait renvoyer null car il est hors de la plage valide");
    }

    @Test
    public void testLargeNegativeInput() {
        assertNull(CharUtil.getCharForNumber(Integer.MIN_VALUE), "Integer.MIN_VALUE devrait renvoyer null car il est hors de la plage valide");
    }

    @Test
    public void testNonAlphabeticPositiveInput() {
        // Testez les valeurs juste au-dessus de la limite alphabétique
        for (int i = 27; i <= 32; i++) {
            assertNull(CharUtil.getCharForNumber(i), i + " devrait renvoyer null car il n'est pas une lettre alphabétique");
        }
    }

    @Test
    public void testAsciiBoundaries() {
        // Testez les limites des valeurs ASCII pour les lettres majuscules
        assertEquals('A', CharUtil.getCharForNumber(65 - 'A' + 1), "65 devrait correspondre à 'A'");
        assertEquals('Z', CharUtil.getCharForNumber(90 - 'A' + 1), "90 devrait correspondre à 'Z'");
        assertNull(CharUtil.getCharForNumber(64 - 'A' + 1), "64 ne devrait pas correspondre à une lettre valide");
        assertNull(CharUtil.getCharForNumber(91 - 'A' + 1), "91 ne devrait pas correspondre à une lettre valide");
    }
}
