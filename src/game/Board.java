package game;

import game.pieces.ChessPiece;
import game.pieces.King;

import java.util.ArrayList;

/**
 * Created by ryan on 1/31/17.
 */
abstract public class Board {


    private ArrayList<ChessPiece> blackPieces = new ArrayList<ChessPiece>();
    private ArrayList<ChessPiece> whitePieces = new ArrayList<ChessPiece>();
    King blackKing;
    King whiteKing;
    private int width;
    private int height;
    private ChessPiece[][] state;

    abstract void initializeBoard(boolean customGame);
    abstract public ChessPiece getPieceAtPosition(int rank, int file);
    abstract public void setPieceAtPosition(ChessPiece piece, int rank, int file);
    abstract public boolean movePiece(ChessPiece piece, int rank, int file);

    public int getSize(){
        return this.width*this.height;
    }

    public ArrayList<ChessPiece> getPieces(Color color){
        if (color == Color.BLACK) {
            return this.blackPieces;
        }
        else{
            return this.whitePieces;
        }
    }

    public Board(){
        this.width = 8;
        this.height = 8;
    }

    public Board(int width, int height){
        this.width = width;
        this.height = height;
    }

    public King getKing(Color color){
        if (color == Color.BLACK){
            return blackKing;
        }
        else{
            return whiteKing;
        }
    }

}
