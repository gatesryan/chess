package game.pieces;
import game.*;
import java.util.ArrayList;
/**
 * Created by ryan on 2/12/17.
 */
public class Wazir extends ChessPiece {


    public Wazir(Color color, int rank, int file){
        this.setRank(rank);
        this.setFile(file);
        this.setColor(color);
        this.setValue(8);
        this.setPosition(new Position(rank, file));
    }


    public ArrayList<Position> getPossibleMoves(Board board){
        ArrayList<Position> moves = new ArrayList<Position>();

        int currentRank = this.getRank();
        int currentFile = this.getFile();

        try {
            ChessPiece pieceAtPosition = board.getPieceAtPosition(currentRank+1, currentFile);
            if (pieceAtPosition == null || pieceAtPosition.getColor() != this.getColor()){
                moves.add(new Position(currentRank+1, currentFile));
            }
        }
        catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }

        try {
            ChessPiece pieceAtPosition = board.getPieceAtPosition(currentRank-1, currentFile);
            if (pieceAtPosition == null || pieceAtPosition.getColor() != this.getColor()){
                moves.add(new Position(currentRank-1, currentFile));
            }
        }
        catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();

        }
        try {
            ChessPiece pieceAtPosition = board.getPieceAtPosition(currentRank, currentFile+1);
            if (pieceAtPosition == null || pieceAtPosition.getColor() != this.getColor()){
                moves.add(new Position(currentRank, currentFile+1));
            }
        }
        catch (IndexOutOfBoundsException ex){

        }
        try {
            ChessPiece pieceAtPosition = board.getPieceAtPosition(currentRank, currentFile-1);
            if (pieceAtPosition == null || pieceAtPosition.getColor() != this.getColor()){
                moves.add(new Position(currentRank, currentFile-1));
            }
        }
        catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();

        }

        return moves;
    }
}
