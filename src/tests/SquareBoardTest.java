package tests;

import game.pieces.*;
import junit.framework.*;
import game.*;

/**
 * Created by ryan on 2/6/17.
 */
public class SquareBoardTest extends TestCase {

    SquareBoard board;
    ChessPiece[][] boardState;

    public void setUp(){
        board = new SquareBoard(8, false);
        boardState = board.getState();
    }


    public void testInitialization(){
        SquareBoard board = new SquareBoard(8, false);

        Rook blackRook1 = new Rook(Color.BLACK,  0,0 );
        Rook blackRook2 = new Rook(Color.BLACK, 0, 7);

        Knight blackKnight1 = new Knight(Color.BLACK, 0, 1);
        Knight blackKnight2 = new Knight(Color.BLACK, 0, 6);

        Bishop blackBishop1 = new Bishop(Color.BLACK, 0, 2);
        Bishop blackBishop2 = new Bishop(Color.BLACK, 0, 5);

        Queen blackQueen = new Queen(Color.BLACK, 0, 3);
        King blackKing = new King(Color.BLACK, 0, 4);


        Assert.assertEquals(boardState[0][0], blackRook1);
        Assert.assertEquals(boardState[0][7], blackRook2);

        Assert.assertEquals(boardState[0][1], blackKnight1);
        Assert.assertEquals(boardState[0][6], blackKnight2);

        Assert.assertEquals(boardState[0][2], blackBishop1);
        Assert.assertEquals(boardState[0][5], blackBishop2);


        Assert.assertEquals(boardState[0][3], blackQueen);
        Assert.assertEquals(boardState[0][4], blackKing);

        for (int file = 0; file < 8; file++){
            Assert.assertEquals(new Pawn(Color.BLACK, 1, file), boardState[1][file]);
        }



        Rook whiteRook1 = new Rook(Color.WHITE,  7,0 );
        Rook whiteRook2 = new Rook(Color.WHITE, 7, 7);

        Knight whiteKnight1 = new Knight(Color.WHITE, 7, 1);
        Knight whiteKnight2 = new Knight(Color.WHITE, 7, 6);

        Bishop whiteBishop1 = new Bishop(Color.WHITE, 7, 2);
        Bishop whiteBishop2 = new Bishop(Color.WHITE, 7, 5);

        Queen whiteQueen = new Queen(Color.WHITE, 7, 3);
        King whiteKing = new King(Color.WHITE, 7, 4);

        Assert.assertEquals(boardState[7][0], whiteRook1);
        Assert.assertEquals(boardState[7][7], whiteRook2);

        Assert.assertEquals(boardState[7][1], whiteKnight1);
        Assert.assertEquals(boardState[7][6], whiteKnight2);

        Assert.assertEquals(boardState[7][2], whiteBishop1);
        Assert.assertEquals(boardState[7][5], whiteBishop2);


        Assert.assertEquals(boardState[7][3], whiteQueen);
        Assert.assertEquals(boardState[7][4], whiteKing);

        for (int file = 0; file < 8; file++){
            Assert.assertEquals(new Pawn(Color.WHITE, 6, file), boardState[6][file]);
        }
    }


    public void testGetPieceAtPosition(){
        ChessPiece piece = board.getPieceAtPosition(1, 3);
        Pawn pawn = new Pawn(Color.BLACK, 1, 3);
        Assert.assertEquals(pawn, piece);


        piece = board.getPieceAtPosition(7, 0);
        Rook rook = new Rook(Color.WHITE, 7, 0);
        Assert.assertEquals(rook, piece);
    }

    public void testSetPieceAtPosition(){

        Pawn pawn = new Pawn(Color.BLACK, 4, 4);
        board.setPieceAtPosition(pawn, 4, 4);

        Assert.assertNotNull(boardState[4][4]);
        Assert.assertEquals(boardState[4][4],  pawn);
    }


    public void testMovePiece(){
        board.movePiece(board.getPieceAtPosition(6, 2), 5, 2);
        Assert.assertNull(board.getPieceAtPosition(6, 2));
        Assert.assertNotNull(board.getPieceAtPosition(5, 2));

        board.movePiece(board.getPieceAtPosition(7, 3), 4, 0);

        Assert.assertNull(board.getPieceAtPosition(7, 3));
        Assert.assertNotNull(board.getPieceAtPosition(4, 0));
    }
}