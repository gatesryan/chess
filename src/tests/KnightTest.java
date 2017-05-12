package tests;

import game.*;
import game.pieces.Knight;
import junit.framework.*;

/**
 * Created by ryan on 2/5/17.
 */
public class KnightTest extends TestCase {

    private SquareBoard board = new SquareBoard(8, false);


    public void testCanMove(){
        board.printBoard();
        System.out.println(board.getSize());
        Knight testKnight = (Knight) board.getPieceAtPosition(7, 1);

        Assert.assertTrue(testKnight.canMove(board, 5, 2));
        Assert.assertTrue(testKnight.canMove(board, 5, 0));

        Assert.assertFalse(testKnight.canMove(board, 5, 5));
        Assert.assertFalse(testKnight.canMove(board, 6, 3));

        board.setPieceAtPosition(testKnight, 2, 3);
        board.printBoard();

        //Test capture
        Assert.assertTrue(testKnight.canMove(board, 0, 2));
        Assert.assertTrue(testKnight.canMove(board, 0, 4));
        Assert.assertTrue(testKnight.canMove(board, 1, 1));
        Assert.assertTrue(testKnight.canMove(board, 1, 5));

        //testing movement
        Assert.assertTrue(testKnight.canMove(board, 3, 1));
        Assert.assertTrue(testKnight.canMove(board, 3, 5));


        // test invalid positions
        Assert.assertFalse(testKnight.canMove(board, 4, 3));

    }

}