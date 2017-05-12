package game; /**
 * Created by ryan on 2/1/17.
 */

import game.pieces.ChessPiece;

import java.util.ArrayList;

public class Player{

    // used to check stalemate condition in main game loop
    private int numberOfPieces = 16;
    private int numberOfMoves;
    private ArrayList<ChessPiece> pieces;

    String name;


    Player(String name, ArrayList<ChessPiece> pieces){
        this.name = name;
        this.numberOfMoves = 0;
        this.pieces = pieces;
    }

    public void incrementNumberOfMoves(){
        numberOfMoves++;
    }

    public int getNumberOfMoves(){
        return numberOfMoves;
    }
}
