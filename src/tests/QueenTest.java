package tests;

import game.*;
import junit.framework.*;
import game.pieces.ChessPiece;

/**
 * Created by ryan on 2/5/17.
 */
public class QueenTest extends TestCase {

    private SquareBoard board;
    ChessPiece testQueen;

    public void setUp(){
        board = new SquareBoard(8, false);
        testQueen = board.getPieceAtPosition(0, 3);
        board.setPieceAtPosition(testQueen, 3, 3);

    }

    /**
     * Tests that queen can move to all valid positions up and down a rank
     */
    public void testRankMovement(){


        // test movement up and down rank
        Assert.assertTrue(testQueen.canMove(board, 3,0));
        Assert.assertTrue(testQueen.canMove(board, 3,1));
        Assert.assertTrue(testQueen.canMove(board, 3,2));
        Assert.assertTrue(testQueen.canMove(board, 3,4));
        Assert.assertTrue(testQueen.canMove(board, 3,5));
        Assert.assertTrue(testQueen.canMove(board, 3,6));
        Assert.assertTrue(testQueen.canMove(board, 3,7));



        Assert.assertFalse(testQueen.canMove(board, 1, 1));



        //test that queen cannot move to pieces occupied by own color
        Assert.assertFalse(testQueen.canMove(board, 1, 3));
        Assert.assertFalse(testQueen.canMove(board, 1, 4));
        Assert.assertFalse(testQueen.canMove(board, 1, 5));

        //test invalid positions
        Assert.assertFalse(testQueen.canMove(board, -1, 3));
        Assert.assertFalse(testQueen.canMove(board, 8, 3));
        Assert.assertFalse(testQueen.canMove(board, 7, -1));



        board.printBoard();


    }

    /**
     * Tests that queen can move to all valid positions up and down a file.
     */
    public void testFileMovement(){
        board.printBoard();

        Assert.assertTrue(testQueen.canMove(board, 2,3));
        Assert.assertTrue(testQueen.canMove(board, 4,3));
        Assert.assertTrue(testQueen.canMove(board, 5,3));

        Assert.assertFalse(testQueen.canMove(board, 3,3));
        Assert.assertFalse(testQueen.canMove(board, 1,3));

    }

    /**
     * Tests that queen can move to all valid positions along a diagonal
     */
    public void testDiagonalMovement(){
        board.printBoard();

        Assert.assertTrue(testQueen.canMove(board,2,2));
        Assert.assertTrue(testQueen.canMove(board, 4, 4));
        Assert.assertTrue(testQueen.canMove(board, 5, 5));

    }

    /**
     * Tests that queen can capture pieces appropriately
     */
    public void testCapture(){
        board.printBoard();

        Assert.assertTrue(testQueen.canMove(board, 6, 3));
        Assert.assertTrue(testQueen.canMove(board, 6, 0));
        Assert.assertFalse(testQueen.canMove(board, 7, 3));
    }
}