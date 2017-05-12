package game.pieces;
import game.*;
import java.util.ArrayList;
/**
 * Created by ryan on 2/12/17.
 */
public class Ferz extends ChessPiece {


    public Ferz(Color color, int rank, int file){
        this.setRank(rank);
        this.setFile(file);
        this.setColor(color);
        this.setValue(7);
        this.setPosition(new Position(rank, file));
    }

    public ArrayList<Position> getPossibleMoves(Board board){
        ArrayList<Position> moves = new ArrayList<Position>();

        int currentRank = this.getRank();
        int currentFile = this.getFile();

        try {
            ChessPiece pieceAtPosition = board.getPieceAtPosition(currentRank+1, currentFile+1);
            if (pieceAtPosition == null || pieceAtPosition.getColor() != this.getColor()){
                moves.add(new Position(currentRank+1, currentFile+1));
            }
        }
        catch (IndexOutOfBoundsException ex){

        }

        try {
            ChessPiece pieceAtPosition = board.getPieceAtPosition(currentRank+1, currentFile-1);
            if (pieceAtPosition == null || pieceAtPosition.getColor() != this.getColor()){
                moves.add(new Position(currentRank+1, currentFile-1));
            }
        }
        catch (IndexOutOfBoundsException ex){

        }
        try {
            ChessPiece pieceAtPosition = board.getPieceAtPosition(currentRank-1, currentFile+1);
            if (pieceAtPosition == null || pieceAtPosition.getColor() != this.getColor()){
                moves.add(new Position(currentRank-1, currentFile+1));
            }
        }
        catch (IndexOutOfBoundsException ex){

        }
        try {
            ChessPiece pieceAtPosition = board.getPieceAtPosition(currentRank-1, currentFile-1);
            if (pieceAtPosition == null || pieceAtPosition.getColor() != this.getColor()){
                moves.add(new Position(currentRank-1, currentFile-1));
            }
        }
        catch (IndexOutOfBoundsException ex){

        }

        return moves;
    }

}
