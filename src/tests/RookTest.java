package tests;

import game.*;
import junit.framework.*;
import game.pieces.ChessPiece;

/**
 * Created by ryan on 2/5/17.
 */
public class RookTest extends TestCase {


    SquareBoard board = new SquareBoard(8, false);
    ChessPiece testRook;

    public void setUp(){
        board = new SquareBoard(8, false);
        testRook = board.getPieceAtPosition(7, 0);

    }

    /**
     * Tests that rook can move in all directions it is allowed to
     */
    public void testCanMove(){

        Assert.assertFalse(testRook.canMove(board, 6,0));
        Assert.assertFalse(testRook.canMove(board, 7, 1));
        Assert.assertFalse(testRook.canMove(board, 7, 2));

    }

    /**
     * Tests that rook can capture pieces on the board
     */
    public void testCapture(){
        board = new SquareBoard(8, false);
        testRook = board.getPieceAtPosition(7, 0);

        board.setPieceAtPosition(testRook, 3, 4);
        Assert.assertTrue(testRook.canMove(board, 1, 4));

        board.movePiece(testRook, 1, 4);
        assertEquals(board.getPieceAtPosition(1,4).getColor(), Color.WHITE);
        assertEquals(board.getPieceAtPosition(1, 4), testRook);
    }

}