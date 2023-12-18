package util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import simpleGame.util.Logger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestLogger {

    // Sauvegarde du flux de sortie standard avant le début des tests.
    private final PrintStream originalOut = System.out;
    // Création d'un flux pour capturer les sorties de la console.
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    // Configuration exécutée avant chaque test.
    @BeforeEach
    public void setUpStreams() {
        // Redirige System.out vers notre flux pour capturer les sorties de la console.
        System.setOut(new PrintStream(outContent));
    }

    // Nettoyage exécuté après chaque test.
    @AfterEach
    public void restoreStreams() {
        // Restaure System.out vers le flux de sortie standard.
        System.setOut(originalOut);
    }

    // Test l'affichage d'un seul message.
    @Test
    public void testLogWithSingleMessage() {
        String testString = "Test message";
        Logger.log(testString);
        // Vérifie que le message est bien présent dans la sortie capturée.
        assertTrue(outContent.toString().contains(testString), "Le message log devrait contenir : " + testString);
    }

    // Test l'affichage de plusieurs messages.
    @Test
    public void testLogWithMultipleMessages() {
        String[] testStrings = {"First message", "Second message", "Third message"};
        // Enregistre plusieurs messages.
        for (String testString : testStrings) {
            Logger.log(testString);
        }

        String output = outContent.toString();
        // Vérifie que chaque message est bien présent dans la sortie capturée.
        for (String testString : testStrings) {
            assertTrue(output.contains(testString), "Le log devrait contenir : " + testString);
        }
    }

    // Test l'affichage d'une chaîne vide.
    @Test
    public void testLogWithEmptyString() {
        Logger.log("");
        // Vérifie que la sortie capturée correspond à un saut de ligne pour une chaîne vide.
        assertEquals("\n", outContent.toString(), "Logger avec une chaîne vide devrait retourner un saut de ligne");
    }

    // Test l'affichage de caractères spéciaux.
    @Test
    public void testLogWithSpecialCharacters() {
        String testString = "Special characters: \n\t";
        Logger.log(testString);
        // Vérifie que les caractères spéciaux sont présents dans la sortie capturée.
        assertTrue(outContent.toString().contains(testString), "Le log devrait gérer correctement les caractères spéciaux");
    }

    // Test l'affichage d'un message null.
    @Test
    public void testLogNullMessage() {
        Logger.log(null);
        // En supposant que Logger traite "null" comme "null\n".
        String expectedOutput = "null\n";
        // Vérifie que "null" est traité et affiché correctement.
        assertEquals(expectedOutput, outContent.toString(), "Logger devrait gérer 'null' comme un message valide");
    }

    // Teste que le buffer de sortie est effacé après chaque appel à Logger.
    @Test
    public void testLogClearBufferAfterEachCall() {
        Logger.log("First call");
        Logger.log("Second call");
        // Sépare la sortie capturée en lignes.
        String[] lines = outContent.toString().split("\n");
        // Vérifie que chaque appel à Logger est sur une ligne séparée.
        assertEquals("First call", lines[0].trim());
        assertEquals("Second call", lines[1].trim());
    }

    // Teste la gestion des exceptions lors de l'utilisation de Logger.
    @Test
    public void testLogIOExceptionHandling() {
        // Crée un faux flux de sortie qui lance une exception lors de l'écriture.
        PrintStream faultyStream = new PrintStream(new ByteArrayOutputStream()) {
            @Override
            public void println(String x) {
                throw new RuntimeException("IOException simulée lors de l'écriture dans System.out");
            }
        };

        // Remplace System.out par le faux flux.
        System.setOut(faultyStream);
        try {
            Logger.log("Ce message devrait déclencher une IOException");
            // Échoue si aucune exception n'est lancée.
            fail("Une RuntimeException aurait dû être lancée en raison de l'IOException simulée");
        } catch (RuntimeException e) {
            // Test réussi si une RuntimeException est attrapée.
            assertEquals("IOException simulée lors de l'écriture dans System.out", e.getMessage());
        } finally {
            // Restaure System.out même en cas d'échec du test.
            restoreStreams();
        }
    }
}
