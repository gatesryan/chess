package tests;

import game.pieces.King;
import game.pieces.Knight;
import junit.framework.*;
import game.*;

/**
 * Created by ryan on 2/6/17.
 */
public class KingTest extends TestCase {

    public SquareBoard board = new SquareBoard(8, false);
    King testKing = (King) board.getPieceAtPosition(0, 4);

    public void setUp() {
        board = new SquareBoard(8, false);
        testKing = (King) board.getPieceAtPosition(0, 4);
    }

    /**
     * Tests that King cannot move when blocked by its own pieces
     */
    public void testCantMoveAtStart() {
        assertFalse(testKing.canMove(board, 5, 4));

    }

    /**
     * Tests whether the king can move in all of positions its specification allows and no more
     */
    public void testMovementInAllDirections() {
        board.setPieceAtPosition(testKing, 3, 5);
        board.printBoard();
        assertTrue(testKing.canMove(board, 3, 6));
        assertTrue(testKing.canMove(board, 3, 4));
        assertTrue(testKing.canMove(board, 2, 4));
        assertTrue(testKing.canMove(board, 2, 6));

        assertTrue(testKing.canMove(board, 4, 5));
        assertTrue(testKing.canMove(board, 4, 6));
        assertTrue(testKing.canMove(board, 4, 4));

        board.generateCheckmateConfiguration();
        testKing = (King) board.getPieceAtPosition(0, 6);

        assertFalse(testKing.canMove(board, 1, 5));
        assertFalse(testKing.canMove(board, 1, 6));
        assertFalse(testKing.canMove(board, 1, 7));


    }

    /**
     * Test whether IsInCheck method works when king is placed in check
     */
    public void testIsInCheck() {

        Knight testKnight = (Knight) board.getPieceAtPosition(7, 1);
        board.setPieceAtPosition(testKnight, 2, 3);
        assertTrue(testKing.isInCheck(board));
    }

    /**
     * Tests that isInCheck method works when king is not in check
     */
    public void testNotInCheck() {
        assertFalse(testKing.isInCheck(board));
    }

    /**
     * Tests game ending conditions of IsInCheckmate method
     */
    public void testIsInCheckMate() {

        Knight testKnight = (Knight) board.getPieceAtPosition(7, 1);
        board.setPieceAtPosition(testKnight, 2, 3);


        assertFalse(testKing.isInCheckMate(board));

        board.generateCheckmateConfiguration();

        testKing = (King) board.getPieceAtPosition(0, 6);
        System.out.println(testKing.getPosition().getRank() + ", " + testKing.getPosition().getFile());
        board.printBoard();

        assertTrue(testKing.isInCheckMate(board));
    }
}
