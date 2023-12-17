package simpleGame.cli;

import java.util.Scanner;

import simpleGame.core.Direction;
import simpleGame.core.Game;
import simpleGame.exception.ImpossibleActionException;
import simpleGame.util.Logger;

public class CLIMain {

    private Game game;
    private InputHandler inputHandler;

    // Constructeur permettant l'injection de dépendances pour le jeu et le mécanisme d'entrée
    public CLIMain(Game game, InputHandler inputHandler) {
        this.game = game;
        this.inputHandler = inputHandler;
    }

    // Méthode pour démarrer le jeu
    public void startGame() {
        while (!game.isGameOver()) {
            System.out.println(game.toString());
            int chosenint;
            // Boucle pour obtenir une direction valide de l'utilisateur
            for (chosenint = -10; chosenint >= Direction.values().length || chosenint < 0; chosenint = inputHandler.nextInt()) {
                int counter = 0;
                // Affichage des directions possibles
                for (Direction d : Direction.values()) {
                    System.out.println(counter + ": " + d.name());
                    counter++;
                }
                Logger.log("Please chose a direction: ");
            }

            // Tentative de jouer un tour avec la direction choisie
            try {
                game.playRound(Direction.values()[chosenint]);
            } catch (ImpossibleActionException e) {
                Logger.log("Cannot perform action (" + e.getMessage() + "). Try again.");
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        InputHandler inputHandler = new ConsoleInputHandler();
        CLIMain cliMain = new CLIMain(game, inputHandler);
        cliMain.startGame();
    }
}

// Interface pour abstraire le mécanisme d'entrée
interface InputHandler {
    int nextInt();
}

// Implémentation concrète de InputHandler utilisant un Scanner pour lire l'entrée de la console
class ConsoleInputHandler implements InputHandler {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public int nextInt() {
        return scanner.nextInt();
    }
}
