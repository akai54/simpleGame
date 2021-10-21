package simpleGame.cli;

import java.util.Scanner;

import simpleGame.core.Direction;
import simpleGame.core.Game;
import simpleGame.exception.ImpossibleActionException;
import simpleGame.util.Logger;

public class CLIMain {

    private static Game game;

    public static void main(String[] args) {

        // We start a game and play it until game is over
        // At each iteration, we ask the direction where the current pawn must play.
        // If the action is impossible, the player can try again.

        game = new Game();
        Scanner scanner = new Scanner(System.in);
        while (!game.isGameOver()) {
            System.out.println(game.toString());
            int chosenint;
            for (chosenint = -10; chosenint >= Direction.values().length
                    || chosenint < 0; chosenint = scanner.nextInt()) {
                int counter = 0;
                for (Direction d : Direction.values()) {
                    System.out.println(counter + ": " + d.name());
                    counter++;
                }
                Logger.log("Please chose a direction: ");
            }

            try {
                game.playRound(Direction.values()[chosenint]);
            } catch (ImpossibleActionException e) {
                Logger.log("Cannot perform action (" + e.getMessage() + "). Try again.");
            }

        }
        scanner.close();

    }

}
