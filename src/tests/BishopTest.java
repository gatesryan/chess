package tests;

import game.pieces.Bishop;
import junit.framework.*;
import game.*;
/**
 * Created by ryan on 2/5/17.
 */
public class BishopTest extends TestCase {

    public static SquareBoard board = new SquareBoard(8, false);

    /**
     * Tests whether the piece can move in all of positions its specification allows and no more
     */
    public void testCanMove(){
        Bishop testBishop = (Bishop) board.getPieceAtPosition(0, 2);

        board.setPieceAtPosition(testBishop, 3, 3);

        Assert.assertTrue(testBishop.canMove(board,2,2));
        Assert.assertTrue(testBishop.canMove(board, 4, 4));
        Assert.assertTrue(testBishop.canMove(board, 5, 5));
    }

}