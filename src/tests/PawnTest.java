package tests;

import game.pieces.Pawn;
import junit.framework.*;
import game.*;
/**
 * Created by ryan on 2/5/17.
 */
public class PawnTest extends TestCase {
    private static Pawn testPawn;
    private static SquareBoard board;

    public void setUp() {
        board = new SquareBoard(8, false);
        testPawn = (Pawn) board.getPieceAtPosition(6, 0);
    }

    /**
     * Tests that pawn can move two spaces on its first move
     */
    public void testFirstMove() {
        Assert.assertTrue(testPawn.canMove(board, 5, 0));
        Assert.assertTrue(testPawn.canMove(board, 4, 0));
    }

    /**
     * Tests that pawn cannot move to spaces it is not supposed to
     */
    public void testInvalidMove() {
        Assert.assertFalse(testPawn.canMove(board, 5, 1));
        Assert.assertFalse(testPawn.canMove(board, 5, -1));
    }

    /**
     * Tests that pawn can capture pieces appropriately
     */
    public void testCapture() {
        Pawn testPawn2 = new Pawn(Color.BLACK, 5, 1);
        board.setPieceAtPosition(testPawn2, 5, 1);
        Assert.assertTrue(testPawn.canMove(board, 5, 1));
        Assert.assertTrue(board.getPieceAtPosition(5, 1).equals(testPawn2));
    }
}
