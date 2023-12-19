    package simpleGame.cli;

    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.DisplayName;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.AfterEach;
    import static org.junit.jupiter.api.Assertions.*;
    import static org.mockito.Mockito.*;

    import java.io.ByteArrayOutputStream;
    import java.io.PrintStream;
    import simpleGame.core.Game;

    public class TestCLIMain {

        // Déclaration des variables pour capturer la sortie console et simuler les dépendances
        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        private final PrintStream originalOut = System.out;
        private Game mockGame;
        private InputHandler mockInputHandler;
        private CLIMain cliMain;

        // Méthode exécutée avant chaque test pour initialiser les objets
        @BeforeEach
        public void setUp() {
            // Redirection de la sortie standard vers un flux pour tester les impressions sur la console
            System.setOut(new PrintStream(outContent));

            // Création de mocks pour les objets Game et InputHandler
            mockGame = mock(Game.class);
            mockInputHandler = mock(InputHandler.class);

            // Initialisation de l'instance de CLIMain avec les mocks
            cliMain = new CLIMain(mockGame, mockInputHandler);
        }

        // Méthode exécutée après chaque test pour restaurer l'état original
        @AfterEach
        public void restoreStreams() {
            // Restauration de la sortie standard originale
            System.setOut(originalOut);
        }

        // Test pour vérifier l'initialisation correcte du jeu dans CLIMain
        @Test
        @DisplayName("Test Game Initialization in CLIMain")
        public void testGameInitialization() {
            // Configuration des comportements simulés pour les méthodes isGameOver et nextInt
            when(mockGame.isGameOver()).thenReturn(false).thenReturn(true);
            when(mockInputHandler.nextInt()).thenReturn(0);

            // Démarrage du jeu
            cliMain.startGame();

            // Vérification que la sortie console contient le message attendu
            assertTrue(getOutput().contains("Please chose a direction: "));
        }

        // Test pour vérifier la gestion correcte des entrées utilisateur dans CLIMain
        @Test
        @DisplayName("Test User Input Handling in CLIMain")
        public void testUserInputHandling() {
            // Configuration des comportements simulés pour les méthodes isGameOver et nextInt
            when(mockGame.isGameOver()).thenReturn(false).thenReturn(true);
            when(mockInputHandler.nextInt()).thenReturn(0);

            // Démarrage du jeu
            cliMain.startGame();

            // Vérification que la méthode nextInt a été appelée sur l'objet mockInputHandler
            verify(mockInputHandler).nextInt();
        }

        // Méthode privée pour récupérer le contenu de la sortie console
        private String getOutput() {
            return outContent.toString();
        }
    }
