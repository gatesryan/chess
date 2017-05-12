package tests;
import junit.framework.*;
import game.*;
import game.pieces.Wazir;
/**
 * Created by ryan on 2/12/17.
 */
public class WazirTest extends TestCase {

    SquareBoard chessboard = new SquareBoard(8, true);
    Wazir testWazir;

    public void setUp(){
        testWazir = (Wazir) chessboard.getPieceAtPosition(2, 4);
        chessboard.setPieceAtPosition(testWazir, 4, 3);
        chessboard.printBoard();
    }

    public void testCanMove(){
        //up
        Assert.assertTrue(testWazir.canMove(chessboard, 3, 3));
        //down
        Assert.assertTrue(testWazir.canMove(chessboard, 5, 3));
        //left
        Assert.assertTrue(testWazir.canMove(chessboard, 4, 2));
        //right
        Assert.assertTrue(testWazir.canMove(chessboard, 4, 4));

    }

    public void testCapture(){
        chessboard.setPieceAtPosition(testWazir, 5, 3);
        Assert.assertTrue(testWazir.canMove(chessboard, 6, 3));

        chessboard.movePiece(testWazir, 6, 3);
        Assert.assertEquals(chessboard.getPieceAtPosition(6,3 ), testWazir);
        Assert.assertEquals(testWazir.getPosition(), new Position(6, 3));


        Assert.assertTrue(testWazir.canMove(chessboard, 6, 2));
        chessboard.movePiece(testWazir, 6, 2);
        Assert.assertEquals(chessboard.getPieceAtPosition(6, 2), testWazir);
        Assert.assertEquals(testWazir.getPosition(), new Position(6, 2));

    }

}
