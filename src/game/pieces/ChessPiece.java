package game.pieces; /**
 * Created by ryan on 1/31/17.
 */
import game.Board;
import game.Color;
import game.Position;

import java.util.ArrayList;

abstract public class ChessPiece {

    private int rank;
    private int file;
    private int value;
    private Color color;
    private ArrayList<Position> possibleMoves;
    private Position position;
    private King king;
    //abstract public void move(int rank, int file);


    public int getValue(){
        return value;
    }
    public Color getColor(){
        return color;
    }
    public int getRank(){
        return rank;
    }
    public int getFile(){
        return file;
    }
    public Position getPosition(){
        return this.position;
    }


    public boolean canMove(Board board, int rank, int file){
        king = this.getColor() == Color.BLACK ? board.getKing(Color.BLACK) : board.getKing(Color.WHITE);

        if (this.getPossibleMoves(board).contains(new Position(rank, file)) && !king.isInCheckAfterMove(board, this, new Position(rank, file))){
            return true;
        }
        return false;
    }

    abstract public ArrayList<Position> getPossibleMoves(Board board);

    public void setPossibleMoves(ArrayList<Position> positions){
        this.possibleMoves = positions;
    }
    public void setPosition(Position pos){
        this.position = pos;
        this.rank = pos.getRank();
        this.file = pos.getFile();
    }
    public void setColor(Color col){
        this.color = col;
    }
    public void setValue(int val){
        value = val;
    }
    public void setRank(int newRank){
        rank = newRank;
    }
    public void setFile(int newFile){
        file = newFile;
    }
    public void setKing(King k){

    }

    public boolean equals(Object o){
        ChessPiece piece = (ChessPiece) o;
        if (piece.getColor() == this.getColor() && piece.getPosition().equals(this.getPosition())){
            return true;
        }
        else{
            return false;
        }
    }



}
