package tests;
import game.pieces.Ferz;
import junit.framework.*;
import game.*;
/**
 * Created by ryan on 2/12/17.
 */
public class FerzTest extends TestCase{
    SquareBoard chessboard = new SquareBoard(8, true);
    Ferz testFerz;

    public void setUp(){
        testFerz = (Ferz) chessboard.getPieceAtPosition(2, 3);

        chessboard.setPieceAtPosition(testFerz, 3, 3);
        chessboard.printBoard();
    }

    /**
     * Tests whether the piece can move in all of positions its specification allows and no more
     */
    public void testCanMove(){
        Assert.assertTrue(testFerz.canMove(chessboard, 2, 2));
        Assert.assertFalse(testFerz.canMove(chessboard, 2, 4));
        Assert.assertTrue(testFerz.canMove(chessboard, 4, 2));
        Assert.assertTrue(testFerz.canMove(chessboard, 4, 4));

        Assert.assertFalse(testFerz.canMove(chessboard, 2, 5));
    }

    /**
     * Tests whether piece can move to space occupied by enemy piece and board responds appropriately.
     */
    public void testCapture(){
        chessboard.setPieceAtPosition(testFerz, 5, 3);
        chessboard.movePiece(testFerz, 6, 2);
        Assert.assertEquals(chessboard.getPieceAtPosition(6, 2), testFerz);
    }

}
