package simpleGame.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import simpleGame.exception.ImpossibleActionException;

import static org.junit.jupiter.api.Assertions.*;


public class TestPawn {

    private Board board;

    @BeforeEach
    public void init() {
        board = new Board(2, 5, 5, 3, 3);

        //supprime tout les pions qui se sont initialisés aléatoirement sur la grille
        board.removeAllPawns();
    }

    @Test
    @DisplayName("Constructor correctly initialized")
    public void testPawnConstructor() {
        Pawn pawn = new Pawn( 'c', 1, 1, board);
        board.addPawn(pawn);

        assertEquals(pawn.getLetter(), 'c');
        assertEquals(pawn.getGold(), 0);
        assertEquals(pawn.getHitpoints(), 6);
        assertTrue(pawn.getPosition().equals(new Position(1,1)));
    }

    @Test
    @DisplayName("Test if the move function works as intended")
    public void testMove() throws ImpossibleActionException{
        Pawn pawn0 = new Pawn( 'c', 0, 1, board);
        board.addPawn(pawn0);
        Position pos0 = new Position(0,0);

        Pawn pawn1 = new Pawn( 'c', 3, 4, board);
        Position pos1 = new Position(4,4);
        board.addPawn(pawn1);

        //test lower corner
        pawn0.move(pos0);
        assertEquals(pawn0.getPosition(), pos0);

        //test higher corner
        pawn1.move(pos1);
        assertEquals(pawn1.getPosition(), pos1);
    }

    @Test
    @DisplayName("Test if the move function returns ImpossibleActionException")
    public void testMoveException() throws ImpossibleActionException{
        Pawn pawn0 = new Pawn( 'c', 0, 0, board);
        Pawn pawn1 = new Pawn( 'c', 4, 4, board);
        board.addPawn(pawn0);
        board.addPawn(pawn1);

        //when out of the grid
        assertThrows(ImpossibleActionException.class, () -> pawn0.move(new Position(-1,0)));
        assertThrows(ImpossibleActionException.class, () -> pawn0.move(new Position(0,-1)));
        assertThrows(ImpossibleActionException.class, () -> pawn1.move(new Position(5,4)));
        assertThrows(ImpossibleActionException.class, () -> pawn1.move(new Position(4,5)));

        //when the new position is not adjacent
        assertThrows(ImpossibleActionException.class, () -> pawn0.move(new Position(1,1)));

        //when the Position is already taken
        Pawn pawn3 = new Pawn( 'c', 3, 4, board);
        board.addPawn(pawn3);
        assertThrows(ImpossibleActionException.class, () -> pawn1.move(new Position(3,4)));
    }

    @Test
    @DisplayName("Test if the pawn take hitpoints")
    public void testSuffer(){
        Pawn pawn = new Pawn( 'c', 0, 0, board);

        pawn.suffer(1);
        assertEquals(pawn.getHitpoints(), 5);

        // Test if severall calls work fine
        pawn.suffer(3);
        assertEquals(pawn.getHitpoints(), 2);
    }

    @Test
    @DisplayName("Test if a pawn taking enough hitpoints is declared dead & is removed from the board")
    public void testSufferAndRemove(){
        Pawn pawn = new Pawn( 'c', 0, 0, board);

        assertFalse(pawn.isDead());
        pawn.suffer(3);
        assertFalse(pawn.isDead());
        pawn.suffer(3);
        assertTrue(pawn.isDead());

        //Test if hitpoints get below 0
        Pawn pawnB = new Pawn( 'B', 1, 0, board);
        board.addPawn(pawnB);
        assertEquals(1, board.numberOfPawns());

        pawnB.suffer(7);
        assertTrue(pawnB.isDead());
        assertEquals(0, board.numberOfPawns());
    }

    @Test
    @DisplayName("Test attack Exception cases")
    public void testAttackExceptions() throws ImpossibleActionException{
        Pawn pawn0 = new Pawn( 'c', 2, 2, board); // is on a bonus square
        Pawn pawn1 = new Pawn( 'd', 4, 2, board);
        board.addPawn(pawn0);
        board.addPawn(pawn1);
        
        //suicide test
        assertThrows(ImpossibleActionException.class, () -> pawn0.attack(new Position(2, 2)));

        //attack an adjacent empty square
        assertThrows(ImpossibleActionException.class, () -> pawn0.attack(new Position(3, 2)));

        //attack a non-adjacent ennemy
        assertThrows(ImpossibleActionException.class, () -> pawn0.attack(new Position(4, 2)));
    }

    @Test
    @DisplayName("Test attack damages")
    public void testAttackDamage() throws ImpossibleActionException{
        Pawn pawn0 = new Pawn( 'c', 2, 2, board); // is on a bonus square
        Pawn pawn1 = new Pawn( 'd', 2, 1, board);
        board.addPawn(pawn0);
        board.addPawn(pawn1);

        //Attack a nearby ennemy - 1dmg
        pawn1.attack(new Position(2, 2));
        assertEquals(5, pawn0.getHitpoints());

        //Attack a nearby ennemy from a bonus square - 2dmg
        pawn0.attack(new Position(2, 1));
        assertEquals(4, pawn1.getHitpoints());
    }

    @Test
    @DisplayName("Test if killing an ennemy gives gold")
    public void testAttackGold() throws ImpossibleActionException{
        Pawn pawn0 = new Pawn( 'c', 2, 2, board); // is on a bonus square
        Pawn pawn1 = new Pawn( 'd', 2, 1, board);
        board.addPawn(pawn0);
        board.addPawn(pawn1);

        assertEquals(0, pawn0.getGold());

        //Attack a nearby ennemy from a bonus square - 2dmg
        pawn0.attack(new Position(2, 1));
        assertEquals(0, pawn0.getGold()); //no gold given if attacking without killing

        // pawn0 attack 2 more times to kill pawn1
        pawn0.attack(new Position(2, 1));
        pawn0.attack(new Position(2, 1));
        assertEquals(1, pawn0.getGold()); 
    }
}
